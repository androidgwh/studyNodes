package com.gwh.lib;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Guo Wenhui
 * 2020/9/3
 **/
class A {
    public static void main(String[] args) {
        String s = "targetPage=";
        try {
            s = s+URLEncoder.encode("products?cumId=", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("encode : "+s);
    }
}
