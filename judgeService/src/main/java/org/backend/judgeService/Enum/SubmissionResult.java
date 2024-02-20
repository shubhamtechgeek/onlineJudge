package org.backend.judgeService.Enum;

import lombok.Data;
import lombok.Getter;

@Getter
public enum SubmissionResult {
    SUCCESS(0),
    COMPILE_ERROR(1),
    RUNTIME_ERROR(2);

    private final int code;

    SubmissionResult(int code) {
        this.code = code;
    }

}

