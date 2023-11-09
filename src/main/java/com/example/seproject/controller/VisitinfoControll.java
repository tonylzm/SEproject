/*******************************************************************************
 * 文件名：VisitinfoControll.java
 * 功能描述：访客信息控制类
 * 作者：tony lzm
 * 创建时间：2020-09-17 10:00
 * 修改记录：
 *******************************************************************************/

package com.example.seproject.controller;

import com.example.seproject.entity.User;
import com.example.seproject.entity.block;
import com.example.seproject.entity.visitinfo;
import com.example.seproject.jpa.UserDao;
import com.example.seproject.jpa.VisitinfoDao;
import com.example.seproject.jpa.blockDao;
import com.example.seproject.service.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
//
@Controller
@RestController
public class VisitinfoControll {

    @Autowired
    VisitinfoDao v;
    @Autowired
    blockDao b;
    @Autowired
    Info info;

    @PostMapping("/addinfo")//前端添加访客信息方法,如果有未访问完的访客信息，不允许添加
    public String addInfo(@RequestBody visitinfo visitInfo) {
        visitinfo visitinfo=v.findByVisitorPhone(visitInfo.getVisitorPhone());
        if(visitinfo!=null){
            String status=visitinfo.getApplicationStatus();
            if(status==null){
                return "该访客存在";
            }
            else if(status.equals("拉黑")){
                return "该访客已拉黑";
            }
            else if(status.equals("拒绝")){
                v.save(visitInfo);
                return "添加成功";
            }
            return "该访客存在";
        }else{
            v.save(visitInfo);
            return "添加成功";
        }
    }

    @GetMapping("/status")//前端查看访客审核状态方法，返回一个字符串
    public String status(@RequestParam("visitorPhone")String visitorPhone){
            visitinfo visitinfo=v.findByVisitorPhone(visitorPhone);
            if(visitinfo==null){
                return "不存在";
            }
            String status=visitinfo.getApplicationStatus();
            return status;
    }

//权限判断，明日需重点完善此部分
    @GetMapping("/power")
    public String power(@RequestParam("visitorPhone")String visitorPhone){
        visitinfo visitinfo=v.findByVisitorPhone(visitorPhone);
        String areas=visitinfo.getVisitAreas();
        //如果仓库字段在访问区域中，返回仓库权限
        if(areas.contains("仓库")){
            return "仓库权限";
        }
        //如果办公区字段在访问区域中，返回办公区权限
        if(areas.contains("办公大楼")){
            return "办公区权限";
        }
        return "无权限";
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
            visitor.setApplicationStatus(null);
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
            info.infooperate(visitor);
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
            info.infooperate(visitor);
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
            info.infooperate(visitor);
        } else {
            return null;
            // 处理访客未找到的情况，例如抛出异常或其他操作
        }
        return visitor;
    }
}
