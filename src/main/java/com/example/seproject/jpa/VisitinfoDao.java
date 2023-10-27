package com.example.seproject.jpa;

import com.example.seproject.entity.visitinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitinfoDao extends JpaRepository<visitinfo,Integer> {
    // 根据 visitorPhone 查询数据
    visitinfo findByVisitorPhone(String visitorPhone);

}
