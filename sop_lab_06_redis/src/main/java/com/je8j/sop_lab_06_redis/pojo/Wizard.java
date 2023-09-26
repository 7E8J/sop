package com.je8j.sop_lab_06_redis.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data // Plain Old Java Object
@Document("Wizard") // collection name (Data Model)
public class Wizard implements Serializable {
    @Id // key value (ObjectId for MongoDB)
    private String _id;
    private String sex;
    private String name;
    private String school;
    private String house;
    private int money;
    private String position;

    public Wizard() {}
    public Wizard(String sex, String name, String school, String house, int money, String position) {
        this.sex = sex;
        this.name = name;
        this.school = school;
        this.house = house;
        this.money = money;
        this.position = position;
    }
    public Wizard(String id, String sex, String name, String school, String house, int money, String position) {
        this._id = id;
        this.sex = sex;
        this.name = name;
        this.school = school;
        this.house = house;
        this.money = money;
        this.position = position;
    }

}
