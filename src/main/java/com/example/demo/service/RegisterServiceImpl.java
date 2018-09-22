package com.example.demo.service;

import com.example.demo.entity.SysUser;
import com.example.demo.vo.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * <p>创建时间: 2018/9/22 20:51 </p>
 *
 * @author shimengqiang
 * @version v1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private SysUserService sysUserService;


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void registerUser(SysUser sysuser, Model model, BindingResult bindingResult) {


        MessageVO messageVO = new MessageVO();

        model.addAttribute("message", messageVO);

        String username = sysuser.getUsername();

        StringBuffer message = new StringBuffer();
        if(bindingResult.hasErrors()){
            for (ObjectError error : bindingResult.getAllErrors()) {
                 message.append(" ").append(error.getDefaultMessage());
                log.info(message.toString());
            }
        }else {
            SysUser userByName = sysUserService.findUserByName(username);

            if (userByName != null) {
                log.info("用户{} 已存在", username);
                message.append("这个" + username + "名字已经被人站了,抓紧换一个！！！！");

            } else {
                log.info("username:{} password{}", username, sysuser.getPassword());
                sysUserService.insertUser(sysuser);
                message.append("注册成功，太好了！");
            }
        }
        messageVO.setExtraInfo(message.toString());

    }
}
