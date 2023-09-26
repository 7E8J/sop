package com.je8j.sop_lab_06_redis.repository;

import com.je8j.sop_lab_06_redis.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WizardService {
    private final WizardRepository repository;

    @Autowired
    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value="wizardList")
    public List<Wizard> retrieveWizards(){
        return repository.findAll();
    }

    @Cacheable(value="wizard", key="#id")
    public Wizard retrieveWizardByID(String id) {
        return repository.findByID(id);
    }

    @CachePut(value="wizard", key="#wizard.getName()")
    @CacheEvict(value="wizardList", allEntries=true)
    public Wizard creteWizard(Wizard wizard) {
        return repository.save(wizard);
    }

    @CachePut(value="wizard", key="#wizard.getName()")
    @CacheEvict(value="wizardList", allEntries=true)
    public Wizard updateWizard(Wizard wizard) {
        return repository.save(wizard);
    }

    @CacheEvict(value="wizard", key="#wizard.getName()")
    @Caching(evict={ @CacheEvict(value="wizardList", allEntries=true) })
    public boolean deleteWizard(Wizard wizard) {
        try {
            repository.delete(wizard);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
