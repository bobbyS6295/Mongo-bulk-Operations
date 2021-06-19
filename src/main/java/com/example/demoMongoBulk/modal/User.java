package com.example.demoMongoBulk.modal;

import org.springframework.data.mongodb.core.mapping.Document;
//Uncomment when using Mongorepository
//@Document(collection = "users")
public class User {


    String _id;
    String name;
    String address;
    int age;


    public User() {
    }

    public User(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
