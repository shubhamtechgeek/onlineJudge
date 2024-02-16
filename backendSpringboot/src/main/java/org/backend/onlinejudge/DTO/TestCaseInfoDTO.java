package org.backend.onlinejudge.DTO;

import lombok.Data;

@Data
public class TestCaseInfoDTO {
    private String input_name;
    private Integer input_size;
    private String output_name;
    private Integer output_size;
    private Integer score;
    private String stripped_output_md5;
}
