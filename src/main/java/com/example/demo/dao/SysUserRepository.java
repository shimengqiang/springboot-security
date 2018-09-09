package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SysUserRepository extends JpaRepository<SysUser ,Long> {

    @Query(value = "select * from sys_user u,sys_user_roles s , sys_role r where u.id = s.uid and s.rid = r.id and u.username = ?1",nativeQuery = true)
    SysUser findByUsername(String username);

}
