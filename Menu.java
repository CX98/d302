package com.d302.controller;

import com.d302.util.Input;

public class Menu {
    public Menu(){
        while (true){
            show();
        }
    }

    //给出操作提示，并根据选择的菜单进行相应的业务操作
    private void show(){
        System.out.println("  ====  d302图书管理系统  ====  ");
        System.out.println("1.增加图书信息");
        System.out.println("2.删除图书");
        System.out.println("3.更新图书信息");
        System.out.println("4.根据id查询图书信息");
        System.out.println("5.查询所有图书信息");
        System.out.println("6.退出系统");

        //从键盘获取操作选项
        int op = Input.getInt("请选择操作选项（1-6）：","选择有误，请重新选择：");
        switch (op){
            case 1:{
                Operator.saveBook();
                break;
            }
            case 2:{
                Operator.deleteBook();
                break;
            }
            case 3:{
                Operator.updateBook();
                break;
            }
            case 4:{
                Operator.findById();
                break;
            }
            case 5:{
                Operator.findAll();
                break;
            }
            case 6:{
                System.out.println("  ...系统已经退出！");
                System.exit(1);
            }default:{
                System.out.println("此操作不在选项之内！");
            }
        }
    }
}
