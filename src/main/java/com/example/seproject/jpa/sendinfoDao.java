package com.example.seproject.jpa;

import com.example.seproject.entity.sendinfo;
import com.example.seproject.entity.visitinfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface sendinfoDao extends JpaRepository<sendinfo,Integer> {

    Page<sendinfo>  findByVisitPhone(String visitPhone, Pageable pageable);


}
