package org.backend.onlinejudge.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {

//    private String userId;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
//    private LocalDateTime createdDate;

}
