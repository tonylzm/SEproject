package com.example.seproject.controller;

import com.example.seproject.entity.User;
import com.example.seproject.entity.block;
import com.example.seproject.entity.visitinfo;
import com.example.seproject.jpa.UserDao;
import com.example.seproject.jpa.VisitinfoDao;
import com.example.seproject.jpa.blockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RestController
public class VisitinfoControll {

    @Autowired
    VisitinfoDao v;
    @Autowired
    blockDao b;
    @Autowired
    UserDao u;

    @GetMapping("/check")//前端登录方法
    public String check(@RequestParam("username")String username, @RequestParam("password")String password){
        User user=u.findByUsernameAndPassword(username,password);
        if(user!=null){
            return "登录成功";
        }
        return "登录失败";
    }

    @PostMapping("/addinfo")
    public String addInfo(@RequestBody visitinfo visitInfo) {
        visitinfo visitinfo=v.findByVisitorPhone(visitInfo.getVisitorPhone());
        if(visitinfo!=null){
            return "该访客已存在";
        }else{
            visitinfo savedVisitor =v.save(visitInfo);
            return "添加成功";
        }
    }



    @GetMapping("/allinfo")//前端查看所有访客信息方法，返回一个数组
    public List allinfo(){
        List all=v.findAll();
        return all;
    }

    @GetMapping("/findinfo")//前端查看特定访客信息方法，返回一个对象
    public visitinfo findinfo(@RequestParam("visitorPhone")String visitorPhone){
        visitinfo visitinfo=v.findByVisitorPhone(visitorPhone);
        return  visitinfo;
    }

    @GetMapping("/blockinfo")//前端查看特定访客信息方法，返回一个对象，为黑名单对象
    public block blockinfo(@RequestParam("visitorPhone")String visitorPhone){
        block block=b.findByVisitorPhone(visitorPhone);
        return  block;
    }

    @PostMapping("/addreason")//前端添加拉黑原因方法
    public block addreason(@RequestBody block request){
        block blocks = b.findByVisitorPhone(request.getVisitorPhone());
        if(blocks!=null){
            blocks.setBlockreason(request.getBlockreason());
            b.save(blocks);
        }
        else{
            return null;
        }
        return blocks;
    }

    @GetMapping("/removeblock")//前端移除黑名单方法
    public block removeblock(@RequestParam("visitorPhone")String visitorPhone){
        block block=b.findByVisitorPhone(visitorPhone);
        visitinfo visitor = v.findByVisitorPhone(visitorPhone);
        if(block!=null && visitor!=null){
            b.delete(block);
            visitor.setApplicationStatus(" ");
            v.save(visitor);
        }
        else{
            return null;
        }
        return block;
    }

    @GetMapping("/vet")//前端同意审核访客方法
    public visitinfo vet(@RequestParam("visitorPhone")String visitorPhone) {
        // 查找具有特定 visitor_phone 的访客
        visitinfo visitor = v.findByVisitorPhone(visitorPhone);
        if (visitor != null) {
            // 修改 applicationStatus 的值为 "通过"
            visitor.setApplicationStatus("通过");
            v.save(visitor);
        } else {
            return null;
            // 处理访客未找到的情况，例如抛出异常或其他操作
        }
        return visitor;
    }
//    @GetMapping("/block")
//    public visitinfo block(@RequestParam("visitorPhone")String visitorPhone){
//        // 查找具有特定 visitor_phone 的访客
//        visitinfo visitor = v.findByVisitorPhone(visitorPhone);
//        if (visitor != null) {
//            // 修改 applicationStatus 的值为 "拉黑"
//            visitor.setApplicationStatus("拉黑");
//            v.save(visitor);
//        } else {
//            return null;
//            // 处理访客未找到的情况，例如抛出异常或其他操作
//        }
//        return visitor;
//    }

    @PostMapping("/block")//前端拉黑访客方法
    public block insertIntoBlock(@RequestBody block request) {
        // 创建一个 BlockInfo 对象并设置属性
        block blockInfo = new block();
        blockInfo.setVisitorPhone(request.getVisitorPhone());
        blockInfo.setVisitorName(request.getVisitorName());
        blockInfo.setAffiliatedUnit(request.getAffiliatedUnit());
        blockInfo.setBlocktime(request.getBlocktime());
        visitinfo visitor = v.findByVisitorPhone(request.getVisitorPhone());
        if (visitor != null) {
            // 修改 applicationStatus 的值为 "拉黑"
            visitor.setApplicationStatus("拉黑");
            v.save(visitor);
            b.save(blockInfo);
        } else {
            return null;
            // 处理访客未找到的情况，例如抛出异常或其他操作
        }
        return blockInfo;
    }



    @GetMapping("/refuse")//前端拒绝审核访客方法
    public visitinfo refuse(@RequestParam("visitorPhone")String visitorPhone){
        // 查找具有特定 visitor_phone 的访客
        visitinfo visitor = v.findByVisitorPhone(visitorPhone);
        if (visitor != null) {
            // 修改 applicationStatus 的值为 "拒绝"
            visitor.setApplicationStatus("拒绝");
            v.save(visitor);
        } else {
            return null;
            // 处理访客未找到的情况，例如抛出异常或其他操作
        }
        return visitor;
    }
}
