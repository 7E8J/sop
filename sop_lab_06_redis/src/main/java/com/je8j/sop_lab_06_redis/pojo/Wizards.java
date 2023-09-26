package com.je8j.sop_lab_06_redis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Wizards implements Serializable {
    private List<Wizard> model;
}
