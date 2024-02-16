package org.backend.onlinejudge.Entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "TestCases")
public class TestCaseInfo {
    private String input_name;
    private Integer input_size;
    private String output_name;
    private Integer output_size;
    private Integer score;
    private String stripped_output_md5;
}
