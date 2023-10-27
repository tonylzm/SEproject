package com.example.seproject.controller;

import com.example.seproject.jpa.UserDao;
//import com.example.seproject.dao.UserMapper;
import com.example.seproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Controller
@RestController
public class UserControll{
    @Autowired
    UserDao a;


    @GetMapping("/getAll")
    public List getAll(){
        List all=a.findAll();
        return all;
    }
    //添加死数据到数据库
    @GetMapping("/add")
    public User add(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("age" )int age, @RequestParam("gender" )int gender){
        User user=new User();
        user.setName(name);
        user.setAge(age);
        user.setId(id);
        user.setGender(gender);
        User save=a.save(user);
        return save;
    }
    @GetMapping("/deleteone")
    public String deleteone(@RequestParam("id")int id){
        a.deleteById(id);
        return "删除成功";

    }
    @GetMapping("/check")
    public String check(@RequestParam("username")String username,@RequestParam("password")String password){
        User user = a.findByUsernameAndPassword(username,password);
        //如果检验成功，返回成功信息
        if(user!=null){
            return "登录成功";
        }
        //如果检验失败，返回失败信息
        else{
            return "登录失败";
        }
    }

}