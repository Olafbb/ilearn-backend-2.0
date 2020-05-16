package com.ilearn.controller;

import com.ilearn.domain.dto.MarkDto;
import com.ilearn.mapper.Mapper;
import com.ilearn.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/i-learn")
public class MarksController {
    @Autowired
    private Mapper mapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/{studentId}/mark/all")
    public List<MarkDto> getMarks(@PathVariable(value = "studentId") Long studentId) {
        return mapper.mapToMarkDtoList(dbService.getMarkDatabase().findAllByStudentId(studentId));
    }
}
