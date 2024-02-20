package org.backend.judgeService.DTO;

import lombok.Data;

import java.util.List;

@Data
public class SubmissionRequest {

    private String problemId;

    private String submissionId;

    private String code;

    private String language;

    private String memoryLimit;

    private String maxMemoryLimit;

    private String maxCpuLimit;

    private String timeLimit;

    private List<TestCase> testCaseList;

}
