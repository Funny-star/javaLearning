package java_study.test1;

import java.util.Scanner;

import java.lang.reflect.Array;



interface shape {
    double getArea();
}

class Circle implements shape{
    double r;

    //    构造方法初始化
    public  Circle(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入圆的半径：");
        this.r = sc.nextDouble();
    }

    public  Circle(double r){
        this.r = r;
    }

    @Override
    public double getArea(){
        return 3.14*r*r;
    }
}

class Rectangle implements shape{
    double h;
    double w;

    public  Rectangle(){

    }

    public  Rectangle(double h,double w){
        this.h = h;
        this.w = w;

    }

    @Override
    public double getArea(){
        return w*h;
    }
}