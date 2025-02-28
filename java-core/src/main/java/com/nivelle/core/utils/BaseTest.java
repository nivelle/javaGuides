package com.nivelle.core.utils;

import java.util.HashMap;

/**
 * 测试用
 *
 * @author fuxinzhong
 * @date 2020/11/20
 */
public class BaseTest {

    public static void main(String[] args) {
        int e = 0;
        int p = 2;
        if ((e = p) == 3) {
            System.out.println("in if  e:" + e + ";p:" + p);
        }
        System.out.println("out if  e:" + e + ";p:" + p);

        new Thread(() -> {
            hashMapTest1();
        }).start();
        hashMapTest1();
        int a = 2;
        if ((a = 1) == 1) {
            System.out.println("先赋值再比较:" + a);
        } else {
            System.out.println("a:=" + a);
        }

        System.out.println("book_news_zyd82273".startsWith("book_news"));

        boolean test = true;

        System.out.println(+0.0F==-0.0F);

        System.out.println(Float.NaN!=2);
        System.out.println(Float.NaN==Float.NaN);
        String[] al = new String[5];
        al[4] = "2";
        System.out.println(al);
    }


    public static void hashMapTest1() {
        HashMap hashMap = new HashMap();
        hashMap.put(1, 1);
        hashMap.put(10, 1);
        System.out.println(hashMap.hashCode() + ":in=> " + Thread.currentThread().getName());
    }
}
