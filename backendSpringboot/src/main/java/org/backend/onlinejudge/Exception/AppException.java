package org.backend.onlinejudge.Exception;

import lombok.Data;
import org.backend.onlinejudge.Enums.ErrorCode;

public class AppException extends RuntimeException{

    private final ErrorCode error ;
    public AppException(ErrorCode error) {
        super();
        this.error = error;
    }
}
