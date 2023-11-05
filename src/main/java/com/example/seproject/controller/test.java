package com.example.seproject.controller;

import com.example.seproject.jpa.VisitinfoDao;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Controller
@RestController
public class test {

    @Autowired
    VisitinfoDao v;
//    @GetMapping("/")
//
//    public String helloWorld() {
//        return "main";
//    }
//    @RequestMapping("/12")
//    public String ftlIndex() {
//        System.out.println("fff");
//        return "ww";
//    }


    @GetMapping("/qrcode")
    @Transactional
    public void generateQrcode(HttpServletResponse response, @RequestParam("visitorPhone")String visitPhone) throws IOException, WriterException {
        // 生成 UUID
        System.out.println("visitPhone:"+visitPhone);
        try {

            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            v.updateUUIDByVisitPhone(visitPhone,uuidStr);
            // 生成二维码
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            int width = 300;
            int height = 300;
            BitMatrix bitMatrix = qrCodeWriter.encode(uuidStr, BarcodeFormat.QR_CODE, width, height);
            // 将二维码写入响应流
            response.setContentType("image/png");
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
