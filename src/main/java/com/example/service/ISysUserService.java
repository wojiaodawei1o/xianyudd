package com.example.service;

import com.example.entity.SysUser;

public interface ISysUserService {

    /**
     * 插入数据
     * @param sysUser
     * @return
     */
    void insertSysUser(SysUser sysUser);

    int queryUser(String str1,String str2);

}
