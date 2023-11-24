package com.example.seproject.service;


import com.example.seproject.entity.History;
import com.example.seproject.entity.visitinfo;
import com.example.seproject.jpa.HistoryDao;
import com.example.seproject.jpa.VisitinfoDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class Info {
    @Autowired
    VisitinfoDao v;
    @Autowired
    HistoryDao h;

    public String infooperate(visitinfo visitinfo){
        if (visitinfo != null) {
            // 创建一个新的UUID
            String id = fetchid();
            // 创建一个新的History对象，用于存储数据
            History history = new History();
            // 使用BeanUtils复制visitinfo对象的属性到history对象
            BeanUtils.copyProperties(visitinfo, history);
            // 将新的History对象保存到history表中
            history.setUUID(id);
            h.save(history);
            //v.deleteByVisitorPhone(visitorPhone);
            return "数据已成功移动到历史记录表并从访问信息表中删除。";
        } else {
            return "未找到与该访客电话号码匹配的数据。";
        }
    }

    public String fetchid(){
        LocalDateTime now = LocalDateTime.now();
        // 格式化日期时间，例如：20231107123456000（年月日时分秒毫秒）
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String id = now.format(formatter);
        return id;
    }


}
