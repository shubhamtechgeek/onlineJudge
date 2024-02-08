package org.backend.onlinejudge.Exception;

import java.io.Serial;

public class UserAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String arg0){super(arg0);}


}
