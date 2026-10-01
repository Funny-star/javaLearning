package com.funny;

import java.util.Scanner;

public class numberSpitting {
    public static void main(String[] args) {
        System.out.println("请输入要拆分的数");
//      拆出整数部分的每一位的数字
        double num, numDigitInt = -1;
        double textNum = 1;
        Scanner sc = new Scanner(System.in);
        num = sc.nextDouble();
        while ((int) num > (int) textNum) {
            numDigitInt++;
            textNum *= 10;
        }
        System.out.println(numDigitInt);

        for (double i = numDigitInt; i >= 0; i--) {

            double temp = Math.pow(10, i);
            int result = (int) num / (int) temp;
            System.out.println(result);
            num -= result * temp;
        }
    }
}


//小数部分由于浮点数精度问题建议使用字符串操作的方式进行解决
//具体实现有时间再说
