package com.example.seproject;

import com.example.seproject.dao.UserMapper;
import com.example.seproject.jpa.UserDao;

public class service {
    UserMapper m;
    //重写check方法
    public String check(String username,String password){
        //如果校验成功，返回success
        if(m.check(username,password)!=null){
            return "success";
        }
        //如果校验失败，返回fail
        else{
            return "fail";
        }
    }
}
