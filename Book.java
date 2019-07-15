package com.d302.entity;

import java.util.Date;

public class Book {
    //属性
    private int id;
    private String title;
    private String author;
    private String publisher;
    private Date pub_date;
    private float price;

    //无参构造
    public Book() {}

    //有参构造1
    public Book(int id, String title, String author, String publisher, Date pub_date, float price) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pub_date = pub_date;
        this.price = price;
    }

    //有参构造2，不含id
    public Book(String title, String author, String publisher, Date pub_date, float price) {
        super();
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pub_date = pub_date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [id：" + id + ", 书名：" + title + ", 作者：" + author + ", 出版社：" + publisher + ", 出版日期："
                + pub_date + ", 价格：" + price + "元]";
    }

    //测试
    public static void main(String []args) {
//		String mls = new Book().toString();
        Book book = new Book();
        System.out.println(book);
    }

}
