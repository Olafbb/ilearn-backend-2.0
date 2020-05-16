package com.ilearn.controller;

import com.ilearn.domain.dto.HomeworkDto;
import com.ilearn.mapper.Mapper;
import com.ilearn.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/i-learn")
public class HomeworkController {
    @Autowired
    private Mapper mapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/{studentId}/homework/all")
    public List<HomeworkDto> getMarks(@PathVariable(value = "studentId") Long studentId) {
        return mapper.mapToHomeworkDtoList(dbService.getHomeworkDatabase().getAllHomeworkByStudentId(studentId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/homework")
    public HomeworkDto updateLesson(@RequestBody HomeworkDto homeworkDto) {
        return mapper.mapToHomeworkDto(dbService.getHomeworkDatabase().saveHomework(mapper.mapToHomework(homeworkDto)));
    }
}
