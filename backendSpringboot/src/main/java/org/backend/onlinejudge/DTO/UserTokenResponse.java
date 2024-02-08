package org.backend.onlinejudge.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserTokenResponse {

    private UserDTO user;
    private String message;
    private String token;


}
