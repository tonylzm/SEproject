package com.example.seproject.jpa;

import com.example.seproject.entity.block;
import com.example.seproject.entity.visitinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface blockDao extends JpaRepository<block,Integer> {
    block findByVisitorPhone(String visitorPhone);
}
