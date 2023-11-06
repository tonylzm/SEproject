package com.example.seproject.controller;

import com.example.seproject.entity.History;
import com.example.seproject.entity.visitinfo;
import com.example.seproject.jpa.HistoryDao;
import com.example.seproject.jpa.VisitinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndControll {
    @Autowired
    HistoryDao h;
    @Autowired
    VisitinfoDao v;

    @GetMapping("/Endvisit")
    public String endvisit(String visitorPhone){
        // 查询visitinfo表中的数据
        visitinfo visitinfo = v.findByVisitorPhone(visitorPhone);

        if (visitinfo != null) {
            // 创建一个新的History对象，用于存储部分数据
            History history = new History();
            history.setVisitorPhone(visitinfo.getVisitorPhone());
            history.setVisitorName(visitinfo.getVisitorName());
            history.setVisitAreas(visitinfo.getVisitAreas());
            history.setArrivedate(visitinfo.getArrivedate());
            history.setArrivetime(visitinfo.getArrivetime());
            history.setLefttime(visitinfo.getLefttime());
            history.setLicensePlate(visitinfo.getLicensePlate());
            // 设置其他需要移动的数据
            // 保存新的历史记录
            h.save(history);
            // 在需要的情况下，你可以从visitinfo表中删除数据
            v.delete(visitinfo);

            return "数据已成功移动到历史记录表并从访问信息表中删除。";
        } else {
            return "未找到与该访客电话号码匹配的数据。";
        }
    }
}
