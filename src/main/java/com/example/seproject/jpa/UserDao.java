package com.example.seproject.jpa;

import com.example.seproject.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsernameAndPassword(String username, String password);
}
//连接users表，并检查登录用户名和密码是否匹配
