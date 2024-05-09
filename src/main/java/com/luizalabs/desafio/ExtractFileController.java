package com.luizalabs.desafio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ExtractFileController {

    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses(){

        return Arrays.asList(new Course(1,"Luiz Godoy", "Luiz Godoy"));

    }
}
