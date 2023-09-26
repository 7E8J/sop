package com.je8j.sop_lab_06_redis.controller;

import com.je8j.sop_lab_06_redis.pojo.Wizard;
import com.je8j.sop_lab_06_redis.pojo.Wizards;
import com.je8j.sop_lab_06_redis.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class WizardController {
    private final WizardService wizardService;

    @Autowired
    public WizardController(WizardService wizardService) {
        this.wizardService = wizardService;
    }

    @RequestMapping(value ="/wizards", method = RequestMethod.GET)
    public ResponseEntity<?> getWizards() {
        List<Wizard> wizards = wizardService.retrieveWizards();
        Wizards listWizard = new Wizards();
        listWizard.setModel(wizards);
        return ResponseEntity.ok(listWizard);
        //ResponseEntity return Object and status 200 OK
    }

    @RequestMapping(value ="/wizards/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWizardById(@PathVariable("id") String id) {
        Wizard wizard = wizardService.retrieveWizardByID(id);
        return ResponseEntity.ok(wizard);
    }

    @RequestMapping(value ="/addWizard/{sex}/{name}/{school}/{house}/{money}/{position}", method = RequestMethod.POST)
    public ResponseEntity<?> createWizard(
            @PathVariable String sex,
            @PathVariable String name,
            @PathVariable String school,
            @PathVariable String house,
            @PathVariable int money,
            @PathVariable String position) {
        Wizard wizard = wizardService.creteWizard(new Wizard(sex, name, school, house, money, position));
        return ResponseEntity.ok(wizard);
    }

    @RequestMapping(value ="/updateWizard/where/{id}/{sex}/{nameNew}/{school}/{house}/{money}/{position}", method = RequestMethod.POST)
    public ResponseEntity<?> updateWizard(
            @PathVariable String id,
            @PathVariable String nameNew,
            @PathVariable String sex,
            @PathVariable String school,
            @PathVariable String house,
            @PathVariable int money,
            @PathVariable String position) {
        Wizard wizard = wizardService.retrieveWizardByID(id);
        if(wizard != null) {
            Wizard wizardUpdate = wizardService.updateWizard(new Wizard(id, sex, nameNew, school, house, money, position));
            return ResponseEntity.ok(wizardUpdate);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @RequestMapping(value ="/deleteWizard/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteWizard(@PathVariable String id) {
        Wizard wizard = wizardService.retrieveWizardByID(id);
        return ResponseEntity.ok(wizardService.deleteWizard(wizard));
    }
}
