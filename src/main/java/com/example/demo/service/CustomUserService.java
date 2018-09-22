package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(CustomUserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.isBlank(username)) {
            return null;
        }
        SysUser byUsername = userDao.findByUserName(username);
        if (byUsername == null) {
            log.info(username + "用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();


        List<SysRole> roles = byUsername.getRoles();
        for (SysRole role : roles) {

            authorities.add(new SimpleGrantedAuthority(role.getName()));
            log.info(role.getName());

        }

        return new User(byUsername.getUsername(), byUsername.getPassword(), authorities);
    }


}
