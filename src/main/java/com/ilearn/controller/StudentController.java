package com.ilearn.controller;

import com.ilearn.domain.dto.StudentDto;
import com.ilearn.mapper.Mapper;
import com.ilearn.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/i-learn")
public class StudentController {
    @Autowired
    private Mapper mapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/student/all")
    public List<StudentDto> getStudents() {
        return mapper.mapToStudentDtoList(dbService.getStudentDatabase().getAllStudents());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/student/{studentId}")
    public StudentDto getStudent(@PathVariable(value = "studentId") Long studentId) {
        return mapper.mapToStudentDto(dbService.getStudentDatabase().getStudent(studentId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/student/{studentId}")
    public void deleteStudent(@PathVariable(value = "studentId") Long studentId) {
        dbService.getStudentDatabase().deleteStudent(studentId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/student")
    public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
        return mapper.mapToStudentDto(dbService.getStudentDatabase().saveStudent(mapper.mapToStudent(studentDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/student", consumes = APPLICATION_JSON_VALUE)
    public void createStudent(@RequestBody StudentDto studentDto) {
        dbService.getStudentDatabase().saveStudent(mapper.mapToStudent(studentDto));

    }

    @RequestMapping(method = RequestMethod.GET, value = "/student/check/{studentId}")
    public boolean checkUserId(@PathVariable(value = "studentId") Long studentId) {
        return dbService.getStudentDatabase().isIdTaken(studentId);
    }
}
