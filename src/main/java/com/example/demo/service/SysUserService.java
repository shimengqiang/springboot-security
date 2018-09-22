package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class SysUserService {

    @Autowired
    private UserDao userDao;
    public void insertUser(SysUser user){

        userDao.insertUser(user);
    }

    public SysUser findUserByName(String name){

        return userDao.findByUserName(name);
    }
}
