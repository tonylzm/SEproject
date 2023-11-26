package com.example.seproject.controller;

import com.example.seproject.entity.sendinfo;
import com.example.seproject.jpa.sendinfoDao;
import com.example.seproject.service.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class SendControll {
    @Autowired
    sendinfoDao s;
    @Autowired
    Info info;

    @PostMapping("/addsub")
    public String addsub(@RequestBody sendinfo sendinfo){
        sendinfo.setUUID(info.fetchid());
        s.save(sendinfo);
        return "success";
    }

    @GetMapping("/getsub")
    public Page<sendinfo> getsub(@RequestParam("visitPhone")String visitPhone,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "pageSize", defaultValue = "8") int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        return s.findByVisitPhone(visitPhone,pageable);
    }

    @GetMapping("/allsub")
    public Iterable<sendinfo> allsub( @RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "8") int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        return s.findAll(pageable);
    }


    @GetMapping("/findsub")

    public sendinfo findsub(@RequestParam("UUID")String UUID){
        return s.findByUUID(UUID);
    }

    @GetMapping("/deletesub")
    public String deletesub(@RequestParam("UUID")String UUID){
        s.deleteByUUID(UUID);
        return "success";
    }

}
