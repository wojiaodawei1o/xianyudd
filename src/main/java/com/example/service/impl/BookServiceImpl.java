package com.example.service.impl;

import com.example.entity.SysUser;
import com.example.entity.book;
import com.example.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<book> queryBookList() {
        String sql = "select * from book";
        List<book> list = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(book.class));
        return list;
    }

    @Override
    public List<book> queryBookListByUserName(String name) {
        String sql = "select * from book where id in (select bookid from SHOPPING_CAR where userid = '"+name+"')";
        List<book> list = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(book.class));
        return list;
    }
}
