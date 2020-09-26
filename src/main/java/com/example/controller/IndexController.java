package com.example.controller;

import com.example.entity.SysUser;
import com.example.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/register")
    public String register(){
        return "/register";
    }

    @PostMapping("/user/registerUser")
    @ResponseBody
    public Map<String, Object> registerUser(@RequestParam("email") String email,
                                            @RequestParam("nickName") String nickName,
                                            @RequestParam("password") String password,
                                            @RequestParam("sex") String sex,
                                            @RequestParam("city") String city,
                                            @RequestParam("county") String county){
        Map<String, Object> map = new HashMap<String, Object>();
        SysUser user = new SysUser();
        user.setEmail(email);
        user.setPetName(nickName);
        user.setPassword(password);
        user.setSex(sex);
        user.setAddressCity(city);
        user.setAddressCounty(county);
        sysUserService.insertSysUser(user);
        map.put("msg","保存成功！");
        return map;
    }

    @PostMapping("/loginIn")
    public String loginIn(@RequestParam("email") String email,
                          @RequestParam("pwd") String pwd){
        int num = sysUserService.queryUser(email,pwd);
        if(num == 0){
            return "/login";
        }else{
            return "/product";
        }
    }
}
