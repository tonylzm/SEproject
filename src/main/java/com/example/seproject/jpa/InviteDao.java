package com.example.seproject.jpa;


import com.example.seproject.entity.invite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteDao extends JpaRepository<invite,Integer> {

    invite findByInviterPhone(String inviterPhone);
    Page<invite> findByStatus(String status, Pageable pageable);
}
