package org.backend.judgeService.Service.Impl;

import org.backend.judgeService.DTO.SubmissionRequest;
import org.backend.judgeService.DTO.SubmissionResponse;
import org.backend.judgeService.DTO.TestCase;
import org.backend.judgeService.DTO.TestResult;
import org.backend.judgeService.Enum.SubmissionResult;
import org.backend.judgeService.Service.JudgeService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Override
    public SubmissionResponse executeCode(SubmissionRequest submissionRequest) {
        SubmissionResponse submissionResponse = new SubmissionResponse();
        try {
            // Create a temporary directory to store the source file
            File tempDir = new File("temp");
            tempDir.mkdirs();

            // Write the code to a temporary Java file
            File sourceFile = new File(tempDir, "Main.java");
            FileWriter fileWriter = new FileWriter(sourceFile);
            fileWriter.write(submissionRequest.getCode());
            fileWriter.close();

            // Compile the Java source file
            int compilationResult = compileCode(sourceFile);
            if (compilationResult != 0) {
                submissionResponse.setResult(SubmissionResult.COMPILE_ERROR.getCode());
                submissionResponse.setMsg("Compilation Error");
                return submissionResponse;
            }

            // Execute the code if there are test cases
            List<TestCase> testCases = submissionRequest.getTestCaseList();
            if (!testCases.isEmpty()) {
                List<TestResult> testResults = executeCodeWithTestCases(tempDir, testCases);
                submissionResponse.setTestResultList(testResults);
            } else {
                // If there are no test cases, execute the code once
                String output = executeCodeOnce(tempDir);
                submissionResponse.setMsg(output);
            }

            // Set response details
            submissionResponse.setSubmissionId(Integer.valueOf(submissionRequest.getSubmissionId()));
            submissionResponse.setResult(SubmissionResult.SUCCESS.getCode());

            // Delete temporary directory and files
            sourceFile.delete();
            tempDir.delete();

        } catch (IOException e) {
            e.printStackTrace();
            submissionResponse.setResult(SubmissionResult.RUNTIME_ERROR.getCode());
            submissionResponse.setMsg("Internal Server Error");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return submissionResponse;
    }

    private int compileCode(File sourceFile) throws IOException, InterruptedException {
        Process compileProcess = Runtime.getRuntime().exec("javac " + sourceFile.getAbsolutePath());
        compileProcess.waitFor();
        return compileProcess.exitValue();
    }

    private List<TestResult> executeCodeWithTestCases(File tempDir, List<TestCase> testCases) throws IOException, InterruptedException {
        List<TestResult> testResults = new ArrayList<>();
        for (TestCase testCase : testCases) {
            TestResult testResult = new TestResult();
            testResult.setId(testCase.getId());

            Process execProcess = Runtime.getRuntime().exec("java -classpath " + tempDir.getAbsolutePath() + " Main");
            BufferedWriter inputWriter = new BufferedWriter(new OutputStreamWriter(execProcess.getOutputStream()));
            inputWriter.write(testCase.getInput());
            inputWriter.flush();
            inputWriter.close();

            BufferedReader outputReader = new BufferedReader(new InputStreamReader(execProcess.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = outputReader.readLine()) != null) {
                output.append(line).append("\n");
            }

            String expectedOutput = testCase.getOutput();
            if (output.toString().trim().equals(expectedOutput.trim())) {
                testResult.setPassed(true);
            } else {
                testResult.setPassed(false);
            }
            testResults.add(testResult);
        }
        return testResults;
    }

    private String executeCodeOnce(File tempDir) throws IOException, InterruptedException {
        Process execProcess = Runtime.getRuntime().exec("java -classpath " + tempDir.getAbsolutePath() + " Main");
        BufferedReader outputReader = new BufferedReader(new InputStreamReader(execProcess.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = outputReader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }
}
