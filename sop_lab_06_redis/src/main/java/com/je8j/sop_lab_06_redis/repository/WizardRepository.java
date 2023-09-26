package com.je8j.sop_lab_06_redis.repository;

import com.je8j.sop_lab_06_redis.pojo.Wizard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository  extends MongoRepository <Wizard, String> {

    @Query(value = "{name : '?0'}")
    public Wizard findByName(String name);

    @Query(value = "{_id : '?0'}")
    public Wizard findByID(String id);

}