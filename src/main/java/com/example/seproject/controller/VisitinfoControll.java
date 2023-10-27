package com.example.seproject.controller;

import com.example.seproject.entity.visitinfo;
import com.example.seproject.jpa.VisitinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RestController
public class VisitinfoControll {

    @Autowired
    VisitinfoDao v;

    @PostMapping("/addinfo") // 使用@PostMapping
    public visitinfo addInfo(@RequestBody visitinfo visitInfo) {
        // visitInfo对象包含了前端传入的数据
        visitinfo savedVisitor = v.save(visitInfo); // 假设VisitInfo类与数据库模型对应
        return savedVisitor;
    }
    @GetMapping("/allinfo")
    public List allinfo(){
        List all=v.findAll();
        return all;
    }
    @GetMapping("/findinfo")
    public visitinfo findinfo(@RequestParam("visitorPhone")String visitorPhone){
        visitinfo visitinfo=v.findByVisitorPhone(visitorPhone);
        return  visitinfo;
    }
}
