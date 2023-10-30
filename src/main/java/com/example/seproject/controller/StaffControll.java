package com.example.seproject.controller;

import com.example.seproject.entity.Internal_staff;
import com.example.seproject.jpa.InStaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class StaffControll {
    @Autowired
    InStaffDao i;

    @GetMapping("/loginstaff")//前端登录方法
    public String loginstaff(@RequestParam("StaffIdcard")String StaffIdcard, @RequestParam("StaffPassword")String StaffPassword){
        Internal_staff internal_staff=i. findByStaffIdcardAndStaffPassword(StaffIdcard,StaffPassword);
        if(internal_staff!=null){
            return "登录成功";
        }
        return "登录失败";
    }

    @PostMapping("/addstaff") // 使用@PostMapping
    //此方法为向数据库中添加员工信息
    public Internal_staff addStaff(@RequestBody Internal_staff request){
        // request对象包含了前端传入的数据
        Internal_staff savedStaff = i.save(request); // 假设VisitInfo类与数据库模型对应
        return savedStaff;
    }

}
