package com.example.seproject.service;

import com.example.seproject.entity.Lockdown;
import com.example.seproject.entity.visitinfo;
import com.example.seproject.jpa.LockdownDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LockService {
    @Autowired
    LockdownDao l;
    public List<String> convertStringToList(String input) {
        // 根据分隔符（例如逗号）拆分输入字符串，并将其转换为List<String>
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void processLockdownData(Lockdown lockdown) {
        String placesAsString = lockdown.getPlace();
        // 将字符串转换为字符串列表
        List<String> places = convertStringToList(placesAsString);
        for (String place : places) {
            Lockdown newLockdown = new Lockdown();
            newLockdown.setPlace(place);
            newLockdown.setReason(lockdown.getReason());
            newLockdown.setStart(lockdown.getStart());
            newLockdown.setEnd(lockdown.getEnd());
            l.save(newLockdown);
        }
    }

    public void lockpower(visitinfo visitinfo){
       String placese=visitinfo.getVisitAreas();
       List<String> places = convertStringToList(placese);
        for (String place : places) {
            Lockdown lock=l.findByPlace(place);
            String start=lock.getStart();
            String end=lock.getEnd();
            if (start == null || end == null) {
                visitinfo.setLockinfo("未管控");
                return;
            }

            String now=visitinfo.getArrivedate()+" "+visitinfo.getArrivetime();

            LocalDateTime time1 = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime time2 = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime time3 = LocalDateTime.parse(now, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            if(time3.isAfter(time1)&&time3.isBefore(time2)){
                visitinfo.setLockinfo("管控");
                return;
            }
        }
        visitinfo.setLockinfo("未管控");
    }


}
