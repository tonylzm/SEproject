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
    @GetMapping("/vet")
    public visitinfo vet(@RequestParam("visitorPhone")String visitorPhone) {
        // 查找具有特定 visitor_phone 的访客
        visitinfo visitor = v.findByVisitorPhone(visitorPhone);
        if (visitor != null) {
            // 修改 applicationStatus 的值为 "通过"
            visitor.setApplicationStatus("通过");
            v.save(visitor);
        } else {
            return null;
            // 处理访客未找到的情况，例如抛出异常或其他操作
        }
        return visitor;
    }
    @GetMapping("/block")
    public visitinfo block(@RequestParam("visitorPhone")String visitorPhone){
        // 查找具有特定 visitor_phone 的访客
        visitinfo visitor = v.findByVisitorPhone(visitorPhone);
        if (visitor != null) {
            // 修改 applicationStatus 的值为 "拉黑"
            visitor.setApplicationStatus("拉黑");
            v.save(visitor);
        } else {
            return null;
            // 处理访客未找到的情况，例如抛出异常或其他操作
        }
        return visitor;
    }
    @GetMapping("/refuse")
    public visitinfo refuse(@RequestParam("visitorPhone")String visitorPhone){
        // 查找具有特定 visitor_phone 的访客
        visitinfo visitor = v.findByVisitorPhone(visitorPhone);
        if (visitor != null) {
            // 修改 applicationStatus 的值为 "拒绝"
            visitor.setApplicationStatus("拒绝");
            v.save(visitor);
        } else {
            return null;
            // 处理访客未找到的情况，例如抛出异常或其他操作
        }
        return visitor;
    }
}
