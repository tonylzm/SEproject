package com.example.seproject.service;

import com.example.seproject.jpa.VisitinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

//时效性检测方法
@Service
public class agingservice {
    //检测时效性
    @Autowired
    VisitinfoDao v;
    @Transactional
    public String aging(String visitPhone){
        String timeRange = v.findTimeByvisitorPhone(visitPhone);
        if (isTimeRangeOverlapping(timeRange)) {
            return "时间范围在当前时间内，具有时效性";

        } else {
            return "时间范围不在当前时间内，已过期";
        }
    }
    public static boolean isTimeRangeOverlapping(String timeRange) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date currentTime = new Date();
            String[] parts = timeRange.split(" - ");
            Date startTime = dateFormat.parse(parts[0]);
            Date endTime = dateFormat.parse(parts[1]);
            // 判断当前时间是否在时间范围内
            return currentTime.after(startTime) && currentTime.before(endTime);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
