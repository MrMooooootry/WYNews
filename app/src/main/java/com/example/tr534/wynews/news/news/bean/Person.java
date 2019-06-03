package com.example.tr534.wynews.news.news.bean;

/**
 * Created by Administrator on 2019/2/22.
 */

public class Person {
    int age;
    int id;
    int sex;
    String adddress;
    String name;
    public Person(Builder builder){
        this.age=builder.age;
        this.id=builder.id;
        this.sex=builder.sex;
        this.adddress=builder.adddress;
        this.name=builder.name;

    }


    public static class Builder{

        int age;
        int id;
        int sex;
        String adddress;
        String name;
        public Builder(){
            this.age=0;
            this.id=0;
            this.sex=0;
            this.adddress=" ";
            this.name=" ";

        }
        public Builder setName(String name) {
            this.name = name;
            return  this;
        }

        public Builder setAdddress(String adddress) {
            this.adddress = adddress;
            return  this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return  this;
        }

        public Builder setId(int id) {
            this.id = id;
            return  this;
        }

        public Builder setSex(int sex) {
            this.sex = sex;
            return  this;
        }
        public Person build(){
            return new Person(this);
        }
    }


}
