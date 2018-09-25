package com.example.demo.controller;

import com.example.demo.annotation.RequestLimit;
import com.example.demo.entity.SysUser;
import com.example.demo.exception.RequestLimitException;
import com.example.demo.service.RegisterServiceImpl;
import com.example.demo.vo.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private RegisterServiceImpl registerService;

    private final static Logger log = LoggerFactory.getLogger(RegisterController.class);


    @RequestLimit(count = 2, time = 1000)
    @RequestMapping(value = "/toRegister",method = RequestMethod.GET)
    public String register(HttpServletRequest request, Model model){

        log.info("开始跳转注册页面");
        model.addAttribute("sysuser",new SysUser());
        return "register";

    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String insertUser(@Valid SysUser sysuser, BindingResult bindingResult, Model model){
        //TODO 统一异常处理  默认权限 赋值
        MessageVO messageVO = new MessageVO();
        messageVO  = registerService.registerUser(sysuser, bindingResult);

        model.addAttribute("message", messageVO);

        return "registerSuccess";

    }


    /**
     * 此方法只处理本类抛出的 RequestLimitException 异常
     * @param ex
     * @return
     */
//    @ExceptionHandler(Exception.class)
    @ExceptionHandler(RequestLimitException.class)
    public String handleEmployeeNotFoundException(Model model, Exception ex){
//        log.error("Requested URL="+request.getRequestURL());
        log.error("Exception Raised="+ex);

        model.addAttribute(ex);
        model.addAttribute("ex", ex);

        return "error";
    }

}
