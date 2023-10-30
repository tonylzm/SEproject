package com.example.seproject.jpa;

import com.example.seproject.entity.Internal_staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InStaffDao extends JpaRepository<Internal_staff,Integer> {
    //员工Id和密码匹配
    Internal_staff findByStaffIdcardAndStaffPassword(String StaffIdcard, String StaffPassword);
}
