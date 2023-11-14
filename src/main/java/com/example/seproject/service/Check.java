package com.example.seproject.service;


import com.example.seproject.jpa.InStaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Check {
    @Autowired
    InStaffDao i;

    public String checkpower(String StaffIdcard){
        String power=i.findByStaffIdcard(StaffIdcard).getMainpower();
        return power;
    }

}
