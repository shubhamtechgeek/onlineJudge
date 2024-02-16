package org.backend.onlinejudge.Util;

import org.backend.onlinejudge.DTO.ProblemDTO;
import org.backend.onlinejudge.DTO.TestCaseInfoDTO;
import org.backend.onlinejudge.DTO.UserDTO;
import org.backend.onlinejudge.Entity.Problem;
import org.backend.onlinejudge.Entity.TestCaseInfo;
import org.backend.onlinejudge.Entity.User;
import org.backend.onlinejudge.Entity.UserContext;
import org.backend.onlinejudge.Enums.Difficulty;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Converter {


    private Converter(){ super(); }


    public static User userDtoToUser(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        return user;
    }


    public static UserDTO userToUserDto(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());

        return userDTO;

    }

    public static ProblemDTO problemToProblemDTO(Problem problem) {

        ProblemDTO problemDTO = new ProblemDTO();

        problemDTO.setProblemId(problem.getProblemId());
        problemDTO.setAuthorId(problem.getAuthor().getUserId());
        problemDTO.setProblemCode(problem.getProblemCode());
        problemDTO.setProblemTitle(problem.getProblemTitle());
        problemDTO.setDescription(problem.getDescription());
        problemDTO.setTimeLimit(problem.getTimeLimit());
        problemDTO.setMemoryLimit(problem.getMemoryLimit());
        problemDTO.setInputDescription(problem.getInputDescription());
        problemDTO.setOutputDescription(problem.getOutputDescription());
        problemDTO.setSubmitCount(problem.getSubmitCount());
        problemDTO.setAcceptCount(problem.getAcceptCount());
        problemDTO.setAcceptRate(problem.getAcceptRate());
        problemDTO.setDifficulty(problem.getDifficulty().toString());
        problemDTO.setAuthorName(problem.getAuthor().getUsername());
        problemDTO.setHint(problem.getHint());
        problemDTO.setCreateDate(problem.getCreateDate().toString());
        problemDTO.setModifiedDate(problem.getModifiedDate().toString());
        problemDTO.setVisible(problem.getVisible());
        problemDTO.setTestCaseId(problem.getTestCaseId());
        problemDTO.setSampleIO(problem.getSampleIO());
        if(problem.getTestCaseInfo() != null){
            problemDTO.setTestCaseInfo(tcToTcDTOList(problem.getTestCaseInfo()));
        }else{
            problemDTO.setTestCaseInfo(null);
        }


        return problemDTO;
    }


    public static Problem problemDTOTOproblem(ProblemDTO problemDTO) {

        Problem problem = new Problem();

        problem.setProblemId(problemDTO.getProblemId());
        problem.setAuthor(UserContext.getCurrentUser());
        problem.setProblemCode(problemDTO.getProblemCode());
        problem.setProblemTitle(problemDTO.getProblemTitle());
        problem.setDescription(problemDTO.getDescription());
        problem.setTimeLimit(problemDTO.getTimeLimit());
        problem.setMemoryLimit(problemDTO.getMemoryLimit());
        problem.setInputDescription(problemDTO.getInputDescription());
        problem.setOutputDescription(problemDTO.getOutputDescription());
        problem.setSubmitCount(problemDTO.getSubmitCount());
        problem.setAcceptCount(problemDTO.getAcceptCount());
        problem.setAcceptRate(problemDTO.getAcceptRate());
        problem.setDifficulty( Difficulty.valueOf(problemDTO.getDifficulty()));
        problem.setHint(problemDTO.getHint());
        problem.setVisible(problemDTO.getVisible());
        problem.setTestCaseId(problemDTO.getTestCaseId());
        problem.setSampleIO(problemDTO.getSampleIO());
//        problemDTO.setTestCaseInfos(tcToTcDTOList(problem.getTestCaseInfos()));

        return problem;
    }

    public static List<TestCaseInfoDTO> tcToTcDTOList(List<TestCaseInfo> testCaseInfoList){

        return testCaseInfoList
                .stream()
                .map(Converter::testCaseInfoToTestCaseInfoDTO)
                .collect(toList());
    }

    public static TestCaseInfoDTO testCaseInfoToTestCaseInfoDTO(TestCaseInfo testCaseInfo){

        TestCaseInfoDTO testCaseInfoDTO = new TestCaseInfoDTO();

        testCaseInfoDTO.setInput_name(testCaseInfo.getInput_name());
        testCaseInfoDTO.setOutput_name(testCaseInfo.getOutput_name());
        testCaseInfoDTO.setScore(testCaseInfo.getScore());
        testCaseInfoDTO.setInput_size(testCaseInfo.getInput_size());
        testCaseInfoDTO.setOutput_size(testCaseInfo.getOutput_size());
        testCaseInfoDTO.setStripped_output_md5(testCaseInfo.getStripped_output_md5());

        return testCaseInfoDTO;
    }

    public static List<ProblemDTO> problemListToProblemDTOList(List<Problem> problemList) {

        return problemList
                .stream()
                .map(Converter::problemToProblemDTO)
                .toList();

    }
}
