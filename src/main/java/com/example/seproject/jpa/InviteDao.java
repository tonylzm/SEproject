package com.example.seproject.jpa;


import com.example.seproject.entity.invite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteDao extends JpaRepository<invite,Integer> {

    invite findByInviterPhone(String inviterPhone);
}
