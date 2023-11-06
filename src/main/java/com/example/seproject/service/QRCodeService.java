package com.example.seproject.service;

import com.example.seproject.jpa.VisitinfoDao;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QRCodeService {
    @Autowired
    VisitinfoDao v; // 假设你已经注入了VisitinfoDao
    public void generateQRCode(String uuidStr, HttpServletResponse response) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            int width = 300;
            int height = 300;
            BitMatrix bitMatrix = qrCodeWriter.encode(uuidStr, BarcodeFormat.QR_CODE, width, height);
            response.setContentType("image/png");
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}