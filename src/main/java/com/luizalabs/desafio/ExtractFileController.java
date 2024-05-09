package com.luizalabs.desafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ExtractFileController {

    @Autowired
    ReadFileService readFileService;

    @RequestMapping("/listdata")
    public void retrieveAllCourses(){

        readFileService.readTextFile();

    }
}
