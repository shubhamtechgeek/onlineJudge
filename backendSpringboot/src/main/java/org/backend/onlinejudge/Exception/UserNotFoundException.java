package org.backend.onlinejudge.Exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;


    public UserNotFoundException(String arg0){super(arg0);}

}
