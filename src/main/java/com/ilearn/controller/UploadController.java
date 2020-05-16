package com.ilearn.controller;

import com.ilearn.service.dropbox.DropBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/v1/i-learn")
public class UploadController {
    @Autowired
    DropBoxService dropBoxService;


    @RequestMapping(method = RequestMethod.POST,value = "/upload")
    public String handleFileUpload(
            @RequestParam("user-file") MultipartFile multipartFile) throws IOException {
//        byte[] bytes = multipartFile.getBytes();
        InputStream content = new ByteArrayInputStream(multipartFile.getBytes());
        dropBoxService.sendFile(content,multipartFile.getOriginalFilename());
        return "file uploaded";
    }
}
