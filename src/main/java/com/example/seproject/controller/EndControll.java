/*******************************************************************************
 * 文件名：EndControll.java
 * 功能描述：历史类访问方法
 * 作者：tony lzm
 * 创建时间：2023-11-01 10:00
 * 修改记录：
 *******************************************************************************/

package com.example.seproject.controller;

import com.example.seproject.entity.History;
import com.example.seproject.jpa.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndControll {
    @Autowired
    HistoryDao h;


    @GetMapping("/history")//获取历史记录
    public Page<History> getAllData(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
            @RequestParam(value = "visitorPhone", defaultValue = "") String visitorPhone) {
        // 创建分页请求对象
        Pageable pageable = PageRequest.of(page, pageSize);
        // 使用分页请求查询历史数据
        Page<History> historyPage = h.findByVisitorPhone(visitorPhone, pageable);
        return historyPage;
    }

    @GetMapping("/detailed")//获取详细信息
    public History getDetailed(@RequestParam("uuid")String uuid){
        return h.findByUUID(uuid);
    }

    @GetMapping("/info")//获取访客信息
    public History getInfo(@RequestParam("visitorPhone")String visitorPhone){
        return h.findByVisitorPhone(visitorPhone);
    }
//设备
}
