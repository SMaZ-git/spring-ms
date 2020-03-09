package com.smaz.ms.model;

import lombok.Data;

@Data
public class UserModel {

    public UserModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name;

    public int age;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
