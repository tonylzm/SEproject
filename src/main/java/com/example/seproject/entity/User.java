package com.example.seproject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//和user表对应的实体类
@Entity
public class User {
    @Id
    private int id;
    private String name;
    private int age;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    private int gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
