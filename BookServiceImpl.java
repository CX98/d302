package com.d302.service;

import com.d302.dao.BookDao;
import com.d302.dao.BookDaoImpl;
import com.d302.entity.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BookServiceImpl implements BookService{
    private BookDao dao;
    public BookServiceImpl(){
        this.dao = new BookDaoImpl();
    }

    @Override
    public int saveBook(Book book) {
        if (book != null){
            String title = book.getTitle();
            if (title.contains("自杀")){
                String newtitle = title.replace("自杀","**");
                book.setTitle(newtitle);
            }
            //如果是金庸的小说，那么价格最低为50.0元，若低于此价格就默认设置为50.0
            if (book.getAuthor().equals("金庸") && book.getPrice() < 50){
                book.setPrice(50.00F);
            }
            return dao.save(book);
        }
        return -1;
    }

    @Override
    public int deleteBook(int id) {
        Book book = this.getBook(id);
        if (book !=null)
            return this.dao.delete(id);
        return -1;
    }

    @Override
    public Book getBook(int id) {
        return this.dao.findById(id);
    }

    @Override
    public int updateBook(Book book) {
        Book bk = this.dao.findById(book.getId());
        if (bk!=null)
            return this.dao.update(book);
        return -1;
    }

    @Override
    public List<Book> findAll(String keyword) {
        if (keyword==null || "".equals(keyword.trim()))
            keyword = "";
        return this.dao.findAll(keyword);
    }

    //-------------测试------------
    public static void main(String []args) throws ParseException {
        BookService service = new BookServiceImpl();
        List<Book> list = service.findAll("刀");

        for (Book items:list){
            System.out.println(items);
        }

//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2011-07-12");
//        Book book = new Book("《自杀英雄传》","金庸","江西教育出版社",date, 75F);
//        if (service.saveBook(book) >0){
//            System.out.println("保存成功！");
//        }else{
//            System.out.println("保存失败！");
//        }
    }
}
