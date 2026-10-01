package com.funny;

import java.util.Scanner;

public class BMI {
    public static  void main(String[] args){

        double weight;
        double height;
        double bmi;

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入您的体重,单位 kg");
        weight = sc.nextDouble();

        System.out.println("请输入您的身高,单位 m");
        height = sc.nextDouble();

        bmi = weight/(height*height);
        System.out.println("您的bim指数为" + bmi);
    }
}
