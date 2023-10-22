package com.example.seproject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    //检查数据库中，如果username和password都匹配，则返回1，否则返回0
    @Select("SELECT COUNT(*) FROM users WHERE username=#{username} AND password=#{password}")
    public int checkUser(String username,String password);
}
