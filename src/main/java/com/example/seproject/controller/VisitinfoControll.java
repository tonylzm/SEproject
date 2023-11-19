/*******************************************************************************
 * 文件名：VisitinfoControll.java
 * 功能描述：访客信息控制类，主要功能是用来存储修改访客信息的API接口方法
 * 作者：tony lzm
 * 创建时间：2023-09-17 10:00
 * 修改记录：
 *******************************************************************************/

package com.example.seproject.controller;

import com.example.seproject.entity.History;
import com.example.seproject.entity.User;
import com.example.seproject.entity.block;
import com.example.seproject.entity.visitinfo;
import com.example.seproject.jpa.UserDao;
import com.example.seproject.jpa.VisitinfoDao;
import com.example.seproject.jpa.blockDao;
import com.example.seproject.service.Check;
import com.example.seproject.service.Info;
import com.example.seproject.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;

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
    @Autowired
    LockService lockService;
    @Autowired
    Check check;

    @PostMapping("/addinfo")//前端添加访客信息方法,如果有未访问完的访客信息，不允许添加
    public String addInfo(@RequestBody visitinfo visitInfo) {
        visitinfo visitinfo=v.findByVisitorPhone(visitInfo.getVisitorPhone());
        lockService.lockpower(visitInfo);
        if(visitinfo!=null){
            String status=visitinfo.getApplicationStatus();
            if(status==null){
                v.save(visitInfo);
                return "该访客未被审核";
            }
            else if(status.equals("拉黑")){
                return "该访客已拉黑";
            }
            else if(status.equals("拒绝") || status.equals("已访问")){
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

    @GetMapping("/pagesallinfo")//对数据进行分页
    public Page<visitinfo> pagesallinfo(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,
            @RequestParam(value = "status") String status)
           {
        // 创建分页请求对象
        Pageable pageable = PageRequest.of(page, pageSize);
               if(Objects.equals(status, "unreviewed")){
                   Page<visitinfo> allinfo = v.findByApplicationStatusIsNull(pageable);
                   return allinfo;
               }else {
                     Page<visitinfo> allinfo = v.findByApplicationStatusIsNotNull(pageable);
                     return allinfo;
               }
    }
//封控
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


    @GetMapping("/blockall")//返回所有黑名单对象
    public List blockall(){
        List all=b.findAll();
        return all;
    }
    @GetMapping("/pagesblockall")//对数据进行分页
    public Page<block> pagesblockall(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "8") int pageSize
           ){
        // 创建分页请求对象
        Pageable pageable = PageRequest.of(page, pageSize);
        // 使用分页请求查询历史数据
        Page<block> blockall = b.findAll(pageable);
        return blockall;
    }




    @PostMapping("/addreason")//前端添加拉黑原因方法
    public block addreason(@RequestBody block request){
        block blocks = b.findByVisitorPhone(request.getVisitorPhone());
        if(blocks!=null){
            blocks.setBlockreason(request.getBlockreason());
            blocks.setBlockpeople(request.getBlockpeople());
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
    public String vet(
            @RequestParam("visitorPhone")String visitorPhone,
            @RequestParam("edit") String edit
    ) {
        // 查找具有特定 visitor_phone 的访客
        visitinfo visitor = v.findByVisitorPhone(visitorPhone);
        String lockinfo=visitor.getLockinfo();
        if (visitor != null) {
            switch (lockinfo){
                case "管控":
                    if (check.checkpower(edit).contains("主管")) {
                        visitor.setApplicationStatus("通过");
                        v.save(visitor);
                        info.infooperate(visitor);
                    }
                    else{
                        return "权限不足";
                    }
                    break;
                default:
                    visitor.setApplicationStatus("通过");
                    v.save(visitor);
                    info.infooperate(visitor);
                    break;

            }
        } else {
            return null;
            // 处理访客未找到的情况，例如抛出异常或其他操作
        }
        return "审核成功";
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
    public String refuse(
            @RequestParam("visitorPhone")String visitorPhone,
            @RequestParam("edit") String edit
    ){
        // 查找具有特定 visitor_phone 的访客
        visitinfo visitor = v.findByVisitorPhone(visitorPhone);
        String lockinfo=visitor.getLockinfo();
        switch (lockinfo) {
            case "管控":
                if (check.checkpower(edit).contains("主管")) {
                    // 修改 applicationStatus 的值为 "拒绝"
                    visitor.setApplicationStatus("拒绝");
                    v.save(visitor);
                    info.infooperate(visitor);
                } else {
                    return "权限不足";
                }
                break;
            default:
                // 修改 applicationStatus 的值为 "拒绝"
                visitor.setApplicationStatus("拒绝");
                v.save(visitor);
                info.infooperate(visitor);
                break;
        }
        return "拒绝成功";
    }
}
