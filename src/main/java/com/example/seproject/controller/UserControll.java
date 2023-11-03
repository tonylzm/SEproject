package com.example.seproject.controller;


import com.example.seproject.entity.User;
import com.example.seproject.jpa.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControll {

    @Autowired
    UserDao u;

    @GetMapping("/check")//前端登录方法
    public String check(@RequestParam("username")String username, @RequestParam("password")String password){
        User user=u.findByUsernameAndPassword(username,password);
        if(user!=null){
            return "登录成功";
        }
        return "登录失败";
    }
}
