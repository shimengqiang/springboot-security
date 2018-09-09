package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.SysUser;
import com.example.demo.service.CustomUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Autowired
    UserDao dao;
    @Test
    public void contextLoads() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        //加密"0"
//        String encode = bCryptPasswordEncoder.encode("adel");
//        System.out.println(encode);

        SysUser sysUser = new SysUser();
        sysUser.setId(4);

        String name = "lili";
        sysUser.setUsername(name);
        sysUser.setPassword(bCryptPasswordEncoder.encode(name));
        dao.insertUser(sysUser);
    }



}
