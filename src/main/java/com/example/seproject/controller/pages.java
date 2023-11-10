package com.example.seproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pages {
    @GetMapping("/")
    public String helloWorld() {
        return "login";
    }
    @RequestMapping("/12")
    public String ftlIndex() {
        System.out.println("fff");
        return "main";
    }
    @RequestMapping("/{page}")
    public String topage(@PathVariable String page){
        return "page";
    }
}
