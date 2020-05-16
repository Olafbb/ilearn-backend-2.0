package com.ilearn.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("classNumber")
    private String classNumber;
    //wychodzi
    @JsonProperty("lessons")
    private List<String> lessons;
    //wychodzi
    @JsonProperty("teachers")
    private List<String> teachers;
    @JsonProperty("userId")
    private Long userId;
    //przychodzi -> wychodzi
    @JsonProperty(value = "lessonsId", required = true)
    private List<Long> lessonsId;
    //przychodzi -> wychodzi
    @JsonProperty(value = "teachersId", required = true)
    private List<Long> teachersId;
    @JsonProperty(value = "marksId", required = true)
    private List<Long> marksId;


    public StudentDto() {
        super();
    }
}
