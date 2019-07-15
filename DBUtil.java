package com.d302.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/d302";
    private static final String USER = "root";
    private static final String PASS = "123456";
    //1.加载数据库驱动程序
    static {
        try {
            Class.forName(DRIVER); //通过反射机制加载驱动程序
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("驱动程序加载失败！");
            e.printStackTrace();
        }
    }

    //2.通过驱动管理器获得数据库连接对象
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement pstmt,
                             Connection conn) {
        //1.关闭结果集
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //2.关闭操作对象
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //3.关闭连接对象
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String []args) {
        System.out.println(DBUtil.getConnection());

    }

}
