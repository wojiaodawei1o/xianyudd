package com.example.controller;

import com.example.entity.SysUser;
import com.example.entity.book;
import com.example.service.IBookService;
import com.example.service.IShopCarService;
import com.example.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IShopCarService shopCarService;

    @RequestMapping("/login")
    public String login(){
        return "/logindd";
    }

    @RequestMapping("/register")
    public String register(){
        return "/registerdd";
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
    @ResponseBody
    public ModelAndView loginIn(@RequestParam("email") String email,
                          @RequestParam("pwd") String pwd){
        ModelAndView mov = null;
        SysUser user = sysUserService.queryUser(email,pwd);
        if(null == user){
            mov = new ModelAndView("/logindd");
        }else{
            //获取图书列表
            List<book> list = bookService.queryBookList();
            mov = new ModelAndView("/productdd");
            mov.addObject("bookList",list);
            mov.addObject("userName",email);
            mov.addObject("petName",user.getPetName());
        }
        return mov;
    }

    @RequestMapping("/shopping")
    @ResponseBody
    public ModelAndView shopping(@RequestParam("userName") String userName,
                                 @RequestParam("petName") String petName){
        ModelAndView mov = null;
        List<book> list = bookService.queryBookListByUserName(userName);
        Double sum1 = 0.0;
        int sum2 = 0;
        for(book b:list){
            sum1+=b.getPrice();
            sum2+=Integer.parseInt(b.getIntegral());
        }
        mov = new ModelAndView("/shoppingdd");
        mov.addObject("bookList",list);
        mov.addObject("sumPrice",sum1);
        mov.addObject("sumScore",sum2);
        mov.addObject("userName",userName);
        mov.addObject("petName",petName);
        return mov;
    }

    @PostMapping("/user/putInCar")
    public void putInCar(@RequestParam("bookId") String bookId,
                         @RequestParam("userName") String userName){
        shopCarService.save(bookId,userName);
    }

    @PostMapping("/user/deleteBook")
    public void deleteBook(@RequestParam("bookId") String bookId,
                         @RequestParam("userName") String userName){
        shopCarService.delete(bookId,userName);
    }

    @PostMapping("/user/deleteAll")
    public void deleteAll(@RequestParam("userName") String userName){
        shopCarService.deleteAll(userName);
    }

}
