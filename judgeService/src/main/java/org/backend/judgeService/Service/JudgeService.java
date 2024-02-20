package org.backend.judgeService.Service;

import org.backend.judgeService.DTO.SubmissionRequest;
import org.backend.judgeService.DTO.SubmissionResponse;

public interface JudgeService {


    SubmissionResponse executeCode(SubmissionRequest submissionRequest);
}
