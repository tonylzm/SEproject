package com.example.seproject.controller;

import com.example.seproject.jpa.InStaffDao;
import com.example.seproject.jpa.VisitinfoDao;
import com.example.seproject.service.QRCodeService;
import com.example.seproject.service.agingservice;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.UUID;

import com.google.zxing.WriterException;

@Controller
@RestController
public class test {

    @Autowired
    VisitinfoDao v;
    @Autowired
    agingservice a;
    @Autowired
    QRCodeService q;
    @Autowired
    InStaffDao s;




//以下是生成二维码的方法，访客二维码，具有时效性判断
    @GetMapping("/qrcode")
    @Transactional
    public void generateQrcode(HttpServletResponse response, @RequestParam("visitorPhone")String visitPhone) throws IOException, WriterException {
        // 生成 UUID
        try {
            switch (a.aging(visitPhone)) {
                case "时间范围在当前时间内，具有时效性" -> {
                    UUID uuid = UUID.randomUUID();
                    String uuidStr = uuid.toString();
                    v.updateUUIDByVisitPhone(visitPhone, uuidStr);
                    q.generateQRCode(uuidStr, response);
                }
                case "时间范围不在当前时间内，已过期" ->{
                    String uuidStr = "000000000000000000000000";
                    String status = "已访问";
                    v.updateUUIDAndStatusByVisitPhone(visitPhone, uuidStr, status);
                    q.generateQRCode(uuidStr, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //以下是生成二维码的方法，员工二维码，无时效性判断
    @GetMapping("/staffqrcode")
    @Transactional
    public void staffqrcode(HttpServletResponse response, @RequestParam("StaffIdcard")String StaffIdcard) throws IOException, WriterException {
        // 生成 UUID
        try {
            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            s.updateUUIDByStaffIdcard(StaffIdcard, uuidStr);
            q.generateQRCode(uuidStr, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
