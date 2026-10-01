package com.funny;

public class fight {
    public  static  void main(String[] args){
        //    定义变量记录我方攻击力
        int attack1 = 220;
//  定义变量记录我方防御
        int defense1 = 85;
//  定义变量记录我方血量
        double blood1 = 1012.5;
//  定义变量记录我方技能加成
        double skill1 = 1.2;

//  定义变量记录敌方攻击力
        int attack2 = 240;
//  定义变量记录敌方防御
        int defense2 = 80;
//  定义变量记录敌方血量
        double blood2 = 1112.5;
//  定义变量记录敌方技能加成
        double skill2 = 1.1;

//  我方普通攻击的伤害
        double damage1 = attack1 - defense2;
        blood2 -= damage1;
        System.out.println(blood2);
    }

}
