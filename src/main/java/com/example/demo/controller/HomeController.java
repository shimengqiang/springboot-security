package com.example.demo.controller;

import com.example.demo.entity.SysUser;
import com.example.demo.vo.MessageVO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String index(Model model) {
        MessageVO msg = new MessageVO("测试标题", "测试内容", "额外信息，只对管理员显示");
        SysUser sysuser = new SysUser();
        model.addAttribute("sysuser", sysuser);
        return "register";
    }

    public static void main(String[] a){


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密"0"
        String encode = bCryptPasswordEncoder.encode("adel");
        System.out.println(encode);
    }

}
