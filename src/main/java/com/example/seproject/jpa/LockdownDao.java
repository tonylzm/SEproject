package com.example.seproject.jpa;


import com.example.seproject.entity.Lockdown;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockdownDao extends JpaRepository<Lockdown,Integer> {

    Lockdown findByPlace(String place);
}
