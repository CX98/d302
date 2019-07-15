package com.d302.util;

import sun.font.TrueTypeFont;
import sun.util.resources.th.CalendarData_th;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//工具类
public class Input {
    //从键盘获取关键字字符串
    public static String getKeyword(String msg){
        System.out.println(msg);
        String str = null;
        BufferedReader bf =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            str = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.trim();
    }



    //从键盘获取非空字符串
    public static String getString(String msg, String err){
        System.out.println(msg);
        String str = null;
        BufferedReader bf =
                new BufferedReader(new InputStreamReader(System.in));
//        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        try {
            while (flag) {
                str = bf.readLine();
                if (!str.trim().equals("")) {
                    flag = false;
                } else {
                    System.out.println(err);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
//            try {
//                bf.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return str.trim();
    }

    //从键盘获取数值，不可为空
    public static float getData(String msg, String err) {
        Scanner sc = new Scanner(System.in);
        Float data = null;
        boolean flag = true;
        System.out.println(msg);
        while (flag){
            String str = sc.nextLine();
            if (str.matches("^\\d+(\\.\\d{1,2})?$")){
                data = Float.parseFloat(str);
                flag = false;
            }else{
                System.out.println(err);
            }
        }
        return data;
    }

    ///---------测试------------
    public static void main(String[] args){
        Date date = Input.getDate("输入日期：","该日期不能为空:");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println("该日期为："+sdf.format(date));

//        float price = Input.getData("输入浮点数：","该数不能为空:");
//        System.out.println("该书的价格为："+price);

//        String s = Input.getString("输入：", "不能为空！");
//        System.out.println("当前输入为："+s);
    }

    //从键盘获取日期值
    public static Date getDate(String msg, String err) {
        Date date = null;
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean flag = true;
        while (flag){
            String str = sc.nextLine();
            if (str.matches("\\d{4}-[0-9]{2}-[0-9]{2}")){
                try {
                    date = sdf.parse(str);
                    flag = false;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                System.out.println(err);
        }

        }
        return date;
    }

    //从键盘获取整型数值
    public static int getInt(String msg, String err) {
        Scanner sc = new Scanner(System.in);
        Integer id = null;
        boolean flag = true;
        System.out.println(msg);
        while (flag){
            String str = sc.nextLine();
            if (str.matches("\\d+")){
                id = Integer.parseInt(str);
                flag = false;
            }else{
                System.out.println(err);
            }
        }
        return id;
    }

    //获取键盘输入字符，可为空值
    public static String getUString(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        String str = sc.nextLine();
        return str.trim();
    }

    //从键盘获取日期值，可为空
    public static Date getUDate(String msg) {
        Date date = null;
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String str = sc.nextLine();
            if (str.matches("\\d{4}-[0-9]{2}-[0-9]{2}")){
                try {
                    date = sdf.parse(str);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        return date;
    }


    //从键盘获取数值，可为空
    public static float getUData(String msg) {
        Scanner sc = new Scanner(System.in);
        float data = -0.1F;
        System.out.println(msg);
        String str = sc.nextLine();
        if (str.matches("^\\d+(\\.\\d{1,2})?$")){
            data = Float.parseFloat(str);
        }
        return data;
    }
}
