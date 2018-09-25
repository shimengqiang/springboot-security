package com.example.demo.service;

import com.example.demo.entity.SysUser;
import com.example.demo.vo.MessageVO;
import org.springframework.validation.BindingResult;

/**
 * <p>创建时间: 2018/9/22 20:50 </p>
 *
 * @author shimengqiang
 * @version v1.0
 */
public interface RegisterService {


    /**
     * @param sysuser
     * @param bindingResult
     */
    MessageVO registerUser(SysUser sysuser, BindingResult bindingResult);



}
