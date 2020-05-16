package com.ilearn.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeworkDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("deadline")
    private Date deadline;

    @JsonProperty("isDone")
    private Boolean isDone;


    @JsonProperty("studentId")
    private Long studentId;

    @JsonProperty("subject")
    private String subject;
}
