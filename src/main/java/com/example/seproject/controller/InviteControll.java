package com.example.seproject.controller;

import com.example.seproject.entity.invite;
import com.example.seproject.entity.visitinfo;
import com.example.seproject.jpa.InviteDao;
import com.example.seproject.service.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController

public class InviteControll {
    @Autowired
    InviteDao i;
    @Autowired
    Check check;

    @PostMapping("/invite")//邀请访客
    public String invite(@RequestBody invite request){
        try {
            invite invite = i.findByInviterPhone(request.getInviterPhone());
            if (invite != null) {
                return "该访客已被邀请";
            } else {
                request.setStatus("未处理");
                i.save(request);
                return "邀请成功,等待审核";
            }
        }catch (Exception e){
            return "邀请失败";
        }
    }
    @GetMapping("/inviteall")
    public Iterable<invite> getAllData(){
        return i.findAll();
    }
@GetMapping("/pagesinvite")
    public Page<invite> getPagesData( @RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "8") int pageSize ){
    Pageable pageable = PageRequest.of(page, pageSize);
    return i.findByStatus("未处理",pageable);
}
    @GetMapping("/inviteinfo")//获取邀请信息
    public invite getInfo(@RequestParam("inviterPhone")String inviterPhone){
        return i.findByInviterPhone(inviterPhone);
    }

    @GetMapping("/invitevet")//前端同意审核访客方法
    public String invitevet(
            @RequestParam("inviterPhone")String inviterPhone,
            @RequestParam("edit") String edit
    ) {
        invite invite = i.findByInviterPhone(inviterPhone);
        if (check.checkpower(edit).contains("主管")) {
            invite.setStatus("通过");
            i.save(invite);
        }
        else{
            return "权限不足";
        }

        return "审核成功";
    }

    @GetMapping("/inviterefuse")//前端同意审核访客方法
    public String inviterefuse(
            @RequestParam("inviterPhone")String inviterPhone,
            @RequestParam("edit") String edit
    ) {
        invite invite = i.findByInviterPhone(inviterPhone);
        if (check.checkpower(edit).contains("主管")) {
            invite.setStatus("拒绝");
            i.save(invite);
        }
        else{
            return "权限不足";
        }

        return "审核成功";
    }

}
