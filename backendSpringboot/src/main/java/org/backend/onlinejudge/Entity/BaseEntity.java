package org.backend.onlinejudge.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.EntityListeners;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    @CreatedDate
    protected LocalDateTime createDate;

    @LastModifiedDate
    protected LocalDateTime modifiedDate;

}
