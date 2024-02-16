package org.backend.onlinejudge.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.backend.onlinejudge.Entity.SampleIO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class ProblemDTO {

    private String problemId;

    private String authorId;
    @NotBlank(message = "Problem code can not be blank")
    private String problemCode;

    private String authorName;

    @NotBlank(message = "problem title can not be blank")
    private String problemTitle;

    @NotBlank(message = "problem description can not be blank")
    private String description;

    @NotNull(message = "time limit can not be null")
    private Integer timeLimit;

    @NotNull(message = "ram limit can not be null")
    private Integer memoryLimit;

    @NotNull(message = "difficulty can not be null")
    private String difficulty;

    @NotBlank(message = "input description can not be blank")
    private String inputDescription;

    @NotBlank(message = "output description can not be blank")
    private String outputDescription;

    @NotEmpty(message = "Sample input/output can not be empty")
    private List<SampleIO> sampleIOList;

    private String sampleIO;

//    @NotNull(message = "Test Data can not null")
    private String testCaseId;

    private List<TestCaseInfoDTO> testCaseInfo;

    private Boolean visible;

    private Set<TagDTO> tagList;

    private String hint;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZZZ", timezone = "IST")
    private String createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZZZ", timezone = "IST")
    private String modifiedDate;

    private Integer acceptCount;

    private Integer submitCount;

    private Double acceptRate;

    private Integer totalScore;
}
