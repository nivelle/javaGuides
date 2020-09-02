package com.nivelle.base.utils;

import java.util.Random;

/**
 * 生成sqL
 *
 * @author fuxinzhong
 * @date 2020/06/17
 */
public class MakeSqlString {


    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("insert into students (name,age,high,gender,cls_id) values");
        for (int i = 0; i < 20; i++) {
            stringBuilder.append("(");
            stringBuilder.append(" \"老").append(i).append("\",");
            stringBuilder.append(new Random().nextInt(20)).append(",");
            stringBuilder.append(new Random().nextInt(200)).append(",");
            if (i / 2 == 0) {
                stringBuilder.append("1").append(",");
            } else {
                stringBuilder.append("2").append(",");
            }
            stringBuilder.append("111").append(")").append(",");
        }

        //System.out.println(stringBuilder.toString());

        //System.out.println(stringBuilder1.toString());
        int initId = 1141;
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i=1142;i<=1255;i++){
            stringBuilder2.append(i);
            stringBuilder2.append(",");
        }
        System.out.println(stringBuilder2.toString());
    }
}
