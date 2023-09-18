package com.example.seproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
@Controller
public class test {

    @GetMapping("/")
    public String helloWorld() {
        return "HW";//返回HW.html
    }
    @RequestMapping("/ftlIndex")
    public String ftlIndex() {
        System.out.println("fff");
        return "HW";
    }

}
