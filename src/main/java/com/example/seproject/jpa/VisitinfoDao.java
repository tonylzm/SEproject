package com.example.seproject.jpa;

import com.example.seproject.entity.visitinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VisitinfoDao extends JpaRepository<visitinfo,Integer> {
    // 根据 visitorPhone 查询数据
    visitinfo findByVisitorPhone(String visitorPhone);
    void deleteByVisitorPhone(String visitorPhone);
    @Modifying
    @Query("UPDATE visitinfo v SET v.UUID = :uuid WHERE v.visitorPhone = :visitPhone")
    void updateUUIDByVisitPhone( String visitPhone,  String uuid);
    //查找arrivedata，arrivetime,lefttime
    @Query("SELECT CONCAT(v.arrivedate,' ', v.arrivetime, ' - ',v.arrivedate, ' ', v.lefttime) FROM visitinfo v WHERE v.visitorPhone = :visitPhone")
    String findTimeByvisitorPhone(String visitPhone);
    //查找applicationStatus
    @Query("SELECT v.applicationStatus FROM visitinfo v WHERE v.visitorPhone = :visitPhone")
    String findApplicationStatusByvisitorPhone(String visitPhone);


}