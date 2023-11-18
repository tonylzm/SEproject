package com.example.seproject.controller;

import com.example.seproject.entity.Internal_staff;
import com.example.seproject.jpa.InStaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class pages {
    @Autowired
    InStaffDao i;
    @GetMapping("/11")
    public String helloWorld() {
        return "login";
    }
    @GetMapping("/12")
    public String ftlIndex() {
        System.out.println("fff");
        return "main";
    }
    @RequestMapping("/{page}")
    public String topage(@PathVariable String page){
        return "page";
    }

    @GetMapping("/handleLogin")
    public String handleLogin(@RequestParam("StaffIdcard") String StaffIdcard, @RequestParam("StaffPassword") String StaffPassword, RedirectAttributes redirectAttributes) {
        // 处理登录逻辑，检查用户名和密码等
        Internal_staff internal_staff = i.findByStaffIdcardAndStaffPassword(StaffIdcard, StaffPassword);
        // 登录成功
        if (internal_staff != null) {
            redirectAttributes.addFlashAttribute("message", "登录成功");
            return "redirect:/12";
        } else {
            // 登录失败的处理逻辑
            return "redirect:/11"; // 或者返回登录页
        }
    }}
