package org.backend.onlinejudge.Util;

import org.backend.onlinejudge.DTO.UserDTO;
import org.backend.onlinejudge.Entity.User;

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
//        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());

        return userDTO;

    }
}
