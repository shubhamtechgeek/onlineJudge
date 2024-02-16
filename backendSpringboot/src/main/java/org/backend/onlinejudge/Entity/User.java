package org.backend.onlinejudge.Entity;



import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "User")
public class User extends BaseEntity{

    @Transient
    private static final String SEQUENCE_NAME = "User_Sequence";

    @Id
    private String userId;

    @Indexed(unique = true)
    private String username;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    private String password;

    private Long acCount = 0L;

    private Long submitCount = 0L;

    private Boolean enabled = true;


    public static String getSequenceName() {return SEQUENCE_NAME;}
}
