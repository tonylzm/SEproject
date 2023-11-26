package com.example.seproject.controller;

import com.example.seproject.entity.Lockdown;
import com.example.seproject.jpa.LockdownDao;
import com.example.seproject.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LockControll {
    @Autowired
    LockdownDao l;
    @Autowired
    LockService lockService;
    @PostMapping("/lockdown")
    public String lockdown(@RequestBody Lockdown lockdown){
        lockService.processLockdownData(lockdown);
        return "success";
    }

    @GetMapping("/findalllock")
    public Iterable<Lockdown> findalllock(){
        return l.findAll();
    }
}
