package com.example.seproject.jpa;

import com.example.seproject.entity.visitinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VisitinfoDao extends JpaRepository<visitinfo,Integer> {
    // 根据 visitorPhone 查询数据
    visitinfo findByVisitorPhone(String visitorPhone);

    @Modifying
    @Query("UPDATE visitinfo v SET v.UUID = :uuid WHERE v.visitorPhone = :visitPhone")
    void updateUUIDByVisitPhone( String visitPhone,  String uuid);
}
//