package org.backend.judgeService.DTO;

import lombok.Data;

import java.util.List;

@Data
public class SubmissionResponse {

    private Integer submissionId;
    private Integer result;
    private Integer timeUsed;
    private Integer memoryUsed;
    private Double score;//todo
    private String msg;

    private List<TestResult> testResultList;

}
