package com.example.service;

import com.example.entity.book;

import java.util.List;

public interface IShopCarService {

    void save(String bookid,String userName);
    void delete(String bookid,String userName);
    void deleteAll(String userName);
}
