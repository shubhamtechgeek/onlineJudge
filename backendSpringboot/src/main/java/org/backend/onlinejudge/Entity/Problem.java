package org.backend.onlinejudge.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.backend.onlinejudge.Enums.Difficulty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import  org.backend.onlinejudge.Entity.TestCaseInfo;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Problem")
public class Problem extends BaseEntity{

    @Transient
    private static final String SEQUENCE_NAME = "Problem_Sequence";

    @Id
    private String problemId;

    private User author;

    private String problemTitle;

    private String problemCode;

    private String description;

    private Integer timeLimit;

    private Integer memoryLimit;

    private Integer submitCount = 0;

    private Integer acceptCount = 0;

    private Double acceptRate = 0.0;

    private String inputDescription;

    private String outputDescription;

    private Difficulty difficulty;

    private String hint;

    private String sampleIO;

    private String testCaseId;

    private Boolean visible;

    private List<TestCaseInfo> testCaseInfo;

    private Integer testCaseScore;

//    private Double acceptRate = 0.0;
//    private Integer submitCount = 0;
//    private Integer acceptCount = 0;
//    private Integer totalScore; // for problem type OI
//    private Set<Tag> tagList = new HashSet<Tag>();
//    private String testCaseScore;
//    private List<Submission> submissionList;
//    private String hint;
//    private User author;

    public static String getSequenceName() {return SEQUENCE_NAME;}

}
