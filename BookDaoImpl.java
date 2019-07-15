package com.d302.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.d302.entity.Book;
import com.d302.util.DBUtil;

public class BookDaoImpl implements BookDao {

    @Override
    public int save(Book book) {
        int i = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into book(title,author,publisher,pub_date,price) " +
                "values(?,?,?,?,?)";

        try{
            conn = DBUtil.getConnection();			//获取数据库连接
            pstmt = conn.prepareStatement(sql);		//得到数据库操作对象
            pstmt.setString(1, book.getTitle());		//设置参数
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            long time = book.getPub_date().getTime();	//将日期转换成long型数据
            pstmt.setDate(4, new java.sql.Date(time)); //用long型数据构造java.sql.Date对象
            pstmt.setFloat(5, book.getPrice());

            i = pstmt.executeUpdate();
        }catch(Exception e){
            System.out.println("数据保存失败!");
            e.printStackTrace();
        }finally{
            DBUtil.close(null, pstmt, conn);
        }

        return i;
    }

    @Override
    public int delete(int id) {
        int i = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete from book where id=?";
        try{
            conn = DBUtil.getConnection();			//获取数据库连接
            pstmt = conn.prepareStatement(sql);		//得到数据库操作对象
            pstmt.setInt(1, id);		//设置参数
            i = pstmt.executeUpdate();	//执行删除操作
        }catch(Exception e){
            System.out.println("数据删除失败!");
            e.printStackTrace();
        }finally{
            DBUtil.close(null, pstmt, conn);
        }
        return i;
    }

    @Override
    public int update(Book book) {
        int i = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update book set title=?,author=?,publisher=?,pub_date=?,price=? where id=?";

        try{
            conn = DBUtil.getConnection();			//获取数据库连接
            pstmt = conn.prepareStatement(sql);		//得到数据库操作对象
            pstmt.setString(1, book.getTitle());		//设置参数
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            long time = book.getPub_date().getTime();	//将日期转换成long型数据
            pstmt.setDate(4, new java.sql.Date(time)); //用long型数据构造java.sql.Date对象
            pstmt.setFloat(5, book.getPrice());
            pstmt.setInt(6, book.getId());
            i = pstmt.executeUpdate();
        }catch(Exception e){
            System.out.println("数据更新失败!");
            e.printStackTrace();
        }finally{
            DBUtil.close(null, pstmt, conn);
        }
        return i;
    }

    @Override
    public Book findById(int id) {
        Book book = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from book where id=?";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                Date pub_date = rs.getDate("pub_date");
                float price = rs.getFloat("price");

                book = new Book(id, title, author, publisher, pub_date, price);
            }
        }catch (Exception e){
            System.out.println("根据id查询失败！");
            e.printStackTrace();
        }finally{
            DBUtil.close(rs, pstmt, conn);
        }
        return book;
    }

    @Override
    public List<Book> findAll(String keyword) {
        List<Book> list = new ArrayList<Book>();
        Book book = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from book where title like ?"+
                " or author like ? or publisher like ?";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+keyword+"%");
            pstmt.setString(2, "%"+keyword+"%");
            pstmt.setString(3, "%"+keyword+"%");
            rs = pstmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                Date pub_date = rs.getDate("pub_date");
                float price = rs.getFloat("price");

                list.add(new Book(id, title, author, publisher, pub_date, price)) ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.close(rs, pstmt, conn);
        }


        return list;
    }

    //测试方法
    public static void main(String[] args) throws Exception {
//        saveBook();
        //deleteBook();
//        updateBook();
//        selectBook();
        BookDao dao = new BookDaoImpl();
        List<Book> list = dao.findAll("金");
        for (Book item:list){
            System.out.println(item.getId()+"|"+item.getPrice()+
                    "|"+item.getTitle()+"|"+item.getAuthor());
        }
    }

    private static void selectBook() throws Exception {
        BookDao dao = new BookDaoImpl();
        Book book = dao.findById(2);
        System.out.println("id | title | author | pulisher | pub_date | price");
        System.out.println(book.getId()+" | "+book.getTitle()+" | "+book.getAuthor()+
                " | "+book.getPublisher()+" | "+book.getPub_date()+" | "+book.getPrice());
    }

    private static void updateBook() throws Exception {
        BookDao dao = new BookDaoImpl();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-06-10");
        Book book = new Book(2,"《鸳鸯刀》","金庸","台湾三联出版社",date, 95F);
        int i = dao.update(book);
        if(i>0){
            System.out.println("数据更新成功!");
        }else{
            System.out.println("数据更新失败!");
        }
    }

    private static void deleteBook() {
        BookDao dao = new BookDaoImpl();
        int i = dao.delete(1);
        if(i>0){
            System.out.println("数据删除成功!");
        }else{
            System.out.println("数据删除失败!");
        }
    }

    private static void saveBook() throws ParseException {
        BookDao dao = new BookDaoImpl();
        Date date = new SimpleDateFormat("yy-MM-dd").parse("2017-06-30");
        Book book = new Book("《上海堡垒》","江南","二十世纪出版社",date, 15F);
        int i = dao.save(book);
        if(i>0){
            System.out.println("数据保存成功!");
        }else{
            System.out.println("数据保存失败!");
        }
    }

}



