package com.example.seproject.jpa;

import com.example.seproject.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryDao extends JpaRepository<History, Integer> {

    History findByVisitorPhone(String visitorPhone);
    History findByUUID(String UUID);

    Page<History> findByVisitorPhone(String visitorPhone, Pageable pageable);

}
