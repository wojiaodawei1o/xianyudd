package com.example.service;

import com.example.entity.book;

import java.awt.print.Book;
import java.util.List;

public interface IBookService {

    List<book> queryBookList();
    List<book> queryBookListByUserName(String name);
}
