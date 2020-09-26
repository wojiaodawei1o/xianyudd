package com.example.service.impl;

import com.example.entity.SysUser;
import com.example.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertSysUser(SysUser sysUser) {
        String sql = "INSERT INTO sys_user(id,email,petName,password,sex,address_city,address_county) " +
                "VALUES (uuid(),'" + sysUser.getEmail() +
                "','" + sysUser.getPetName() +
                "','" + sysUser.getPassword() +
                "','" + sysUser.getSex() +
                "','" + sysUser.getAddressCity() +
                "','" + sysUser.getAddressCounty() +
                "')";
        System.out.println(sql);
        jdbcTemplate.execute(sql);
    }

    public int queryUser(String str1,String str2){
        String sql = "select * from sys_user where (email = '"+str1+"' or petName = '"+str1+"') and password = '"+str2+"' ";
        List<SysUser> list = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(SysUser.class));
        return list.size();
    }
}
