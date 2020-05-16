package com.ilearn.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lastname")
    private String lastname;
    //wychodzi
    @JsonProperty("lessons")
    private List<String> lessons;
    //wychodzi
    @JsonProperty("students")
    private List<String> students;
    //przychodzi -> wychodzi
    @JsonProperty(value = "lessonsId", required = true)
    private List<Long> lessonsId;
    //przychodzi -> wychodzi
    @JsonProperty(value = "studentsId", required = true)
    private List<Long> studentsId;

    public TeacherDto() {
        super();
    }
}
