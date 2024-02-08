package org.backend.onlinejudge.Service;

import org.backend.onlinejudge.DTO.UserDTO;
import org.backend.onlinejudge.DTO.UserTokenResponse;
import org.springframework.stereotype.Service;


public interface UserService {


    UserDTO signUp(UserDTO user);

    String logout(String token);

    UserDTO getUserProfile(String username);

}
