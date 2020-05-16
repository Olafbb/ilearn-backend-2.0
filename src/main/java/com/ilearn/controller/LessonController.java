package com.ilearn.controller;

import com.ilearn.domain.dto.LessonDto;
import com.ilearn.mapper.Mapper;
import com.ilearn.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/i-learn")
public class LessonController {
    @Autowired
    private Mapper mapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/{studentId}/lesson/all")
    public List<LessonDto> getSchedule(@PathVariable(value = "studentId") Long studentId) {
        return mapper.mapToLessonDtoList(dbService.getLessonDatabase().findAllByStudentId(studentId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/lesson/{lessonId}")
    public LessonDto getLesson(@PathVariable(value = "lessonId") Long lessonId) {
        return mapper.mapToLessonDto(dbService.getLessonDatabase().getLesson(lessonId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/lesson/{lessonId}")
    public void deleteLesson(@PathVariable(value = "lessonId") Long lessonId) {
        dbService.getLessonDatabase().deleteLesson(lessonId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/lesson")
    public LessonDto updateLesson(@RequestBody LessonDto lessonDto) {
        return mapper.mapToLessonDto(dbService.getLessonDatabase().saveLesson(mapper.mapToLesson(lessonDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/lesson", consumes = APPLICATION_JSON_VALUE)
    public void createLesson(@RequestBody LessonDto lessonDto) {
        dbService.getLessonDatabase().saveLesson(mapper.mapToLesson(lessonDto));
    }

}
