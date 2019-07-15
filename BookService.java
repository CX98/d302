package com.d302.service;

import com.d302.entity.Book;

import java.util.List;

public interface BookService {

    //存书的方法
    public abstract int saveBook(Book book);

    //删除书本
    int deleteBook(int id);

    //查询一本书
    Book getBook(int id);

    //根据id更新一本书的信息
    int updateBook(Book book);

    //根据传入关键字模糊查询书籍
    List<Book>  findAll(String keyword);

}
