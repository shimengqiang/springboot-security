package com.example.demo.controller;

import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RestController("/register")
public class WebController {

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertUser(@ModelAttribute(value = "sysuser")  SysUser sysuser){

        sysUserService.insertUser(sysuser);

        return "ok";

    }

}
