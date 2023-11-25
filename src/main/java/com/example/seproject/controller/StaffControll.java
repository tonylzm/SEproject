package com.example.seproject.controller;

import com.example.seproject.config.StaffSpecification;
import com.example.seproject.entity.Internal_staff;
import com.example.seproject.jpa.InStaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.seproject.service.Check;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class StaffControll {
    @Autowired
    InStaffDao i;
    @Autowired
    Check check;

    @GetMapping("/loginstaff")//前端登录方法
    public String loginstaff(@RequestParam("StaffIdcard") String StaffIdcard, @RequestParam("StaffPassword") String StaffPassword) {
        Internal_staff internal_staff = i.findByStaffIdcardAndStaffPassword(StaffIdcard, StaffPassword);
        if (internal_staff != null) {
            return "登录成功";
        }
        return "登录失败";
    }



    @PostMapping("/addstaff") // 使用@PostMapping
    //此方法为向数据库中添加员工信息
    public Internal_staff addStaff(@RequestBody Internal_staff request) {
        // request对象包含了前端传入的数据
        request.setMainpower("系统管理员");
        Internal_staff savedStaff = i.save(request); // 假设VisitInfo类与数据库模型对应
        return savedStaff;
    }

    @PostMapping("/checkpower")
    public String checkpower(@RequestParam("StaffIdcard") String StaffIdcard) {
        Internal_staff internal_staff = i.findByStaffIdcard(StaffIdcard);
        String power = internal_staff.getStaffAffiliatedUnit() + internal_staff.getStaffPosition();
        return power;
    }

    @GetMapping("/staffinfo")
    public Internal_staff staffinfo(@RequestParam("StaffIdcard") String StaffIdcard) {
        Internal_staff internal_staff = i.findByStaffIdcard(StaffIdcard);
        internal_staff.setStaffPassword("*******");
        return internal_staff;
    }

    @GetMapping("/allstaff")
    public Iterable<Internal_staff> allstaff() {
        Iterable<Internal_staff> internal_staffs = i.findAll();
        return internal_staffs;
    }

    @GetMapping("/pagesallstaff")
    public Page<Internal_staff> pagesallstaff(@RequestParam("page") int page,
                                              @RequestParam("pageSize") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Internal_staff> internal_staffs = i.findAll(pageable);
        return internal_staffs;
    }

    @GetMapping("/updatepower")//编辑员工权限，需要主管权限
    public String updatepower(
            @RequestParam("edit") String edit,
            @RequestParam("StaffIdcard") String StaffIdcard,
            @RequestParam("mainpower") String mainpower,
            @RequestParam("secondarypower") String secondarypower) {
        if (check.checkpower(edit).contains("主管")) {
            {
                Internal_staff internal_staff = i.findByStaffIdcard(StaffIdcard);
                internal_staff.setMainpower(mainpower);
                internal_staff.setSecondarypower(secondarypower);
                i.save(internal_staff);
                return "修改成功";
            }
        }
        return "权限不足";
    }
    @PostMapping("/staffsearch")
    public Page<Internal_staff> staffsearch(@RequestBody Internal_staff filter,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        StaffSpecification spec=new StaffSpecification(filter);
        return i.findAll(spec,pageable);

    }

    @GetMapping("/updatestaff")//编辑员工信息，需要主管权限
    public String updatestaff(
            @RequestParam("edit") String edit,
            @RequestParam("StaffIdcard") String StaffIdcard,
            @RequestParam("StaffName") String StaffName,
            @RequestParam("StaffAffiliatedUnit") String StaffAffiliatedUnit,
            @RequestParam("StaffPosition") String StaffPosition,
            @RequestParam("StaffPhone") String StaffPhone,
            @RequestParam("StaffPassword") String StaffPassword) {
        if (check.checkpower(edit).contains("主管")) {
            {
                Internal_staff internal_staff = i.findByStaffIdcard(StaffIdcard);
                internal_staff.setStaffName(StaffName);
                internal_staff.setStaffAffiliatedUnit(StaffAffiliatedUnit);
                internal_staff.setStaffPosition(StaffPosition);
                internal_staff.setStaffPhone(StaffPhone);
                internal_staff.setStaffPassword(StaffPassword);
                i.save(internal_staff);
                return "修改成功";
            }
        }
        return "权限不足";
    }
}
