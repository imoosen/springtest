package com.imoosen.util;

/**
 * Created by Administrator on 2017/6/29 0029.
 */
public class Test {

    public static void change(String str){
        str="welcome";
    }
    public static void main(String[] args) {
        String tel = "12464341312";
        String str = "12464341312";

//
//        String temp = tel.replaceAll("4341","####");
//        System.out.println(temp);
        change(str);
        System.out.println(str);
    }

}
