package com.ilearn.controller;

import com.ilearn.domain.dto.UserDto;
import com.ilearn.mapper.Mapper;
import com.ilearn.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/i-learn")
public class UserController {
    @Autowired
    private DbService dbService;
    @Autowired
    private Mapper mapper;
    @RequestMapping(method = RequestMethod.POST, value = "/user", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        dbService.getUserDatabase().saveUser(mapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/all")
    public List<UserDto> getUsers() {
        return mapper.mapToUserDtoList(dbService.getUserDatabase().getAllUsers());
    }

}
