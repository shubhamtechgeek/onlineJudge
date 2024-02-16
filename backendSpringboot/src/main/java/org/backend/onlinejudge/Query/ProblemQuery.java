package org.backend.onlinejudge.Query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.backend.onlinejudge.Enums.Difficulty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProblemQuery {

    private String problemCode;

    private String title;

//    private String tags;

    private Difficulty difficulty;

    private Boolean visible;

    private String keyword;

}
