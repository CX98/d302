package com.d302.controller;

import com.d302.entity.Book;
import com.d302.service.BookService;
import com.d302.service.BookServiceImpl;
import com.d302.util.Input;
import com.sun.deploy.security.SelectableSecurityManager;

import java.awt.image.VolatileImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Operator {
    private static BookService service = new BookServiceImpl();
    //从键盘获取书本信息，并将其存入数据库
    public static void saveBook(){
        //1.创建一本书的对象，并初始化其属性
        String title = Input.getString("输入书名称：", "书名不能为空，请重新输入：");
        String author = Input.getString("作者：","作者姓名不能为空：");
        String publisher = Input.getString("出版社名称：","出版社名称不能为空：");
        Date pub_date = Input.getDate("出版日期：","出版日期不能为空：");
        float price = Input.getData("书的价格：","输入有误，请重新输入：");

        Book book = new Book(title,author,publisher,pub_date,price);

        //2.调用服务层，将其保存到数据库中
        int i = service.saveBook(book);
        if (i>0){
            System.out.println("书籍的信息保存成功("+book+")！");
        }else{
            System.out.println("书籍的信息保存失败！");
        }
    }

    public static void updateBook(){
        //1.获取书籍编号
        int  id = Input.getInt("请输入要更改的书籍编号","书籍编号不能为空");
        //2.查询该编号书籍是否存在
        Book book = service.getBook(id);
        //3.对当前书本对象的属性进行更新
        if (book!=null){
            String title = Input.getUString("更新书名（原书名"+book.getTitle()+")");
            if (!title.equals(""))
            book.setTitle(title);

            String author = Input.getUString("修改作者："+book.getAuthor());
            if (!author.equals(""))
            book.setAuthor(author);

            String publisher = Input.getUString("修改出版社："+book.getPublisher());
            if (!publisher.equals(""))
            book.setPublisher(publisher);

            Date date = Input.getUDate("修改出版日期："+book.getPub_date());
            if (date!=null)
            book.setPub_date(date);

            float price = Input.getUData("修改图书价格："+book.getPrice());
            if (price>0)
            book.setPrice(price);

            if (service.updateBook(book)>0){
                System.out.println("数据更新成功！");
            }else{
                System.out.println("数据更新失败！");
            }

        }
    }

    //查询单个数据
    public static void findById(){
        //1.输入一个整型编号
        int id = Input.getInt("输入要查询的编号","输入有误，请输入一个正整数：");

        //2.查询此编号的信息
        Book book = service.getBook(id);

        if (book!=null){
            System.out.println("查询结果：");
            System.out.println(book);
        }else{
            System.out.println("查询结果：此id："+id+" 不存在！");
        }
    }

    //模糊查询
    public static void findAll(){
        //1.先从键盘获取要查询的关键字
        String keyword = Input.getKeyword("请输入要查询的关键字，如果不输入则查找全部内容");
        //2.调用服务层查询数据
        List<Book> list = service.findAll(keyword);
        //3.判断数据集合的大小进行显示
        int size = list.size();
        if (size>0){
            System.out.println("查询到如下结果：");
            for (Book items:list)
                System.out.println(items);
        }else {
            System.out.println("...没有查询到相关信息！");
        }
    }

    public static void main(String args[]){
        saveBook();
//        findById();
//        findAll();
    }

    public static void deleteBook() {
        int id = Input.getInt("请输入要删除的id：","该id不能为空：");

        if (service.getBook(id)!=null){
            System.out.println("该数据已删除");
            service.deleteBook(id);
        }else {
            System.out.println("该id不在数据库内。");
        }
    }
}
