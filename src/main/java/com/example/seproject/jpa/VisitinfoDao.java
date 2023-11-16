package com.example.seproject.jpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.seproject.entity.visitinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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

    // 根据applicationStatus是否为空来查询数据
    Page<visitinfo> findByApplicationStatusIsNotNull(Pageable pageable);

    // 根据applicationStatus是否为空来查询数据
    Page<visitinfo> findByApplicationStatusIsNull(Pageable pageable);

}