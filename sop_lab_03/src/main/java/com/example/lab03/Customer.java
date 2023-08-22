package com.example.lab03;

public class Customer {
    private String ID;
    private String name;
    private boolean sex;
    private int age;

    Customer() {
        this.setID("");
        this.setName(null);
        this.setSex("female");
        this.setAge(0);
    }

    Customer(String ID, String n, String s, int a) {
        this.setID(ID);
        this.setName(n);
        this.setSex(s);
        this.setAge(a);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if(sex.equalsIgnoreCase("male")) {
            this.sex = true;
        } else if (sex.equalsIgnoreCase("female")) {
            this.sex = false;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = Math.max(age, 0);
    }
}