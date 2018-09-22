package com.example.demo.controller;

import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import com.example.demo.vo.MessageVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @Autowired
    private SysUserService sysUserService;

    private final static Logger log = LoggerFactory.getLogger(RegisterController.class);


    @RequestMapping(value = "/toRegister",method = RequestMethod.GET)
    public String register(Model model){

        log.info("开始跳转注册页面");
        SysUser sysUser = new SysUser();

        model.addAttribute("sysuser",sysUser);
        return "register";

    }


    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertUser(@ModelAttribute(value = "sysuser")  SysUser sysuser, Model model){
        //TODO 逻辑应该放到service层
        MessageVO messageVO = new MessageVO();
        String username = sysuser.getUsername();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(sysuser.getPassword())) {
            log.info("sysuser null");

            messageVO.setExtraInfo("sysuser null");

            return "error";
        }
        SysUser userByName = sysUserService.findUserByName(username);

        if (userByName != null) {
            log.info("用户{} 已存在", username);
            messageVO.setExtraInfo("这个"+username+"名字已经被人站了,换一个！！！！");


        }else {

            log.info("username:{} password{}", username, sysuser.getPassword());

            sysUserService.insertUser(sysuser);
            messageVO.setExtraInfo("注册成功");
        }

        model.addAttribute("message",messageVO);

        return "registerSuccess";

    }

}
