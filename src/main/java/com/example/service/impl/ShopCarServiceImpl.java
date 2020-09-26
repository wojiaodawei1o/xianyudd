package com.example.service.impl;

import com.example.entity.book;
import com.example.service.IBookService;
import com.example.service.IShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCarServiceImpl implements IShopCarService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(String bookid, String userName) {
        String sql = "INSERT INTO SHOPPING_CAR(bookid,userid) " +
                "VALUES ('" + bookid +
                "','" + userName +
                "')";
        System.out.println(sql);
        jdbcTemplate.execute(sql);
    }

    @Override
    public void delete(String bookid, String userName) {
        String sql = "DELETE FROM SHOPPING_CAR WHERE bookid = '"+bookid+"' and userid = '"+userName+"'";
        System.out.println(sql);
        jdbcTemplate.execute(sql);
    }


    @Override
    public void deleteAll(String userName) {
        String sql = "DELETE FROM SHOPPING_CAR WHERE userid = '"+userName+"'";
        System.out.println(sql);
        jdbcTemplate.execute(sql);
    }
}
