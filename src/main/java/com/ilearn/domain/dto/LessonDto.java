package com.ilearn.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("topic")
    private String topic;
    @JsonProperty("day")
    private String day;
    @JsonProperty("lessonNr")
    private Integer lessonNr;
    @JsonProperty("link")
    private String link;
    @JsonProperty("classNumber")
    private String classNumber;
    //wychodzi
    @JsonProperty("students")
    private List<String> students;
    //przychodzi -> wychodzi
    @JsonProperty("studentsId")
    private List<Long> studentsId;
    //wychodzi
    @JsonProperty("teachers")
    private List<String> teachers;
    //przychodzi -> wychodzi
    @JsonProperty("teachersId")
    private List<Long> teachersId;

    public LessonDto() {
        super();
    }
}
