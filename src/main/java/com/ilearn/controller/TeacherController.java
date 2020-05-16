package com.ilearn.controller;

import com.ilearn.domain.dto.TeacherDto;
import com.ilearn.mapper.Mapper;
import com.ilearn.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/i-learn")
public class TeacherController {
    @Autowired
    private Mapper mapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/teacher/all")
    public List<TeacherDto> getSchedule() {
        return mapper.mapToTeacherDtoList(dbService.getTeacherDatabase().getAllTeachers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teacher/{teacherId}")
    public TeacherDto getLesson(@PathVariable(value = "teacherId") Long teacherId) {
        return mapper.mapToTeacherDto(dbService.getTeacherDatabase().getTeacher(teacherId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/teacher/{teacherId}")
    public void deleteLesson(@PathVariable(value = "teacherId") Long teacherId) {
        dbService.getTeacherDatabase().deleteTeacher(teacherId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/teacher")
    public TeacherDto updateLesson(@RequestBody TeacherDto teacherDto) {
        return mapper.mapToTeacherDto(dbService.getTeacherDatabase().saveTeacher(mapper.mapToTeacher(teacherDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/teacher", consumes = APPLICATION_JSON_VALUE)
    public void createLesson(@RequestBody TeacherDto teacherDto) {
        dbService.getTeacherDatabase().saveTeacher(mapper.mapToTeacher(teacherDto));

    }
}
