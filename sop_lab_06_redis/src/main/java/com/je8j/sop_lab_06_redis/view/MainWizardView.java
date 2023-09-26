package com.je8j.sop_lab_06_redis.view;

import com.je8j.sop_lab_06_redis.pojo.Wizard;
import com.je8j.sop_lab_06_redis.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Route("/mainPage.it")
public class MainWizardView extends VerticalLayout {
    private int index = -1;
    private List<Wizard> wizards = fetch().getModel();

    private MainWizardView(){
        fetch();

//        Fullname
        TextField FullNameField = new TextField();
        FullNameField.setPlaceholder("Fullname");
        this.add(FullNameField);


//        Gender
        RadioButtonGroup<String> GenderRadio = new RadioButtonGroup<>();
        GenderRadio.setLabel("Gender:");
        GenderRadio.setItems("Male", "Female");
        GenderRadio.setValue("Pending");
        this.add(GenderRadio);

//      Positon
        ComboBox<String> PositionField = new ComboBox<>();
        PositionField.setPlaceholder("Position");
        PositionField.setItems("Student", "Teacher");
        this.add(PositionField);

//        Dollar
        TextField DollarField = new TextField();
        DollarField.setLabel("Dollar");
        DollarField.setPrefixComponent(new Span("$"));
        this.add(DollarField);

//        School
        ComboBox<String> SchoolBox = new ComboBox<>();
        SchoolBox.setLabel("School");
        SchoolBox.setPlaceholder("School");
        SchoolBox.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        this.add(SchoolBox);

//        House
        ComboBox<String> HouseBox = new ComboBox<>();
        HouseBox.setLabel("House");
        HouseBox.setPlaceholder("House");
        HouseBox.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slytherin");
        this.add(HouseBox);

//        Navigator
        Button LeftButton = new Button("Left");
        Button CreateButton = new Button("Create");
        Button UpdateButton = new Button("Update");
        Button DeleteButton = new Button("Delete");
        Button RightButton = new Button("Right");
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.add(LeftButton, CreateButton, UpdateButton, DeleteButton, RightButton);
        this.add(hLayout);

        LeftButton.addClickListener(event -> {
            if(index >= 0) {
                index--;
                indexWizards(FullNameField, PositionField, DollarField, GenderRadio, SchoolBox, HouseBox);
            }
        });

        RightButton.addClickListener(event -> {
            if(index < wizards.size() - 1) {
                index++;
                indexWizards(FullNameField, PositionField, DollarField, GenderRadio, SchoolBox, HouseBox);
            }
        });

        CreateButton.addClickListener(event -> {
           if(!FullNameField.isEmpty() && !GenderRadio.isEmpty() && !SchoolBox.isEmpty() && !HouseBox.isEmpty() && !DollarField.isEmpty()) {
               WebClient.create()
                       .post()
                       .uri("http://localhost:8080/addWizard/"+GenderRadio.getValue()+"/"+FullNameField.getValue()+"/"+SchoolBox.getValue()+"/"+HouseBox.getValue()+"/"+DollarField.getValue()+"/"+PositionField.getValue())
                       .retrieve()
                       .bodyToMono(String.class)
                       .block();
               Notification notification = Notification.show("Wizard Created!");
           } else {
               Notification notification = Notification.show("Please Fullfilled Text Field");
           }
        });

        DeleteButton.addClickListener(event -> {
            if(!FullNameField.isEmpty() && !GenderRadio.isEmpty() && !SchoolBox.isEmpty() && !HouseBox.isEmpty() && !DollarField.isEmpty()) {
                WebClient.create()
                        .post()
                        .uri("http://localhost:8080/deleteWizard/"+wizards.get(index).get_id())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                Notification notification = Notification.show("Wizard Deleted!");
            } else {
                Notification notification = Notification.show("Please Select Wizard!");
            }
        });

        UpdateButton.addClickListener(event -> {
            if(!FullNameField.isEmpty() && !GenderRadio.isEmpty() && !SchoolBox.isEmpty() && !HouseBox.isEmpty() && !DollarField.isEmpty()) {
                WebClient.create()
                        .post()
                        .uri("http://localhost:8080/updateWizard/where/"+wizards.get(index).get_id()+"/"+GenderRadio.getValue()+"/"+FullNameField.getValue()+"/"+SchoolBox.getValue()+"/"+HouseBox.getValue()+"/"+DollarField.getValue()+"/"+PositionField.getValue())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                Notification notification = Notification.show("Wizard Updated!");
            } else {
                Notification notification = Notification.show("Please Select Wizard!");
            }
        });
    }

    public Wizards fetch() {
        return WebClient.create()
                .get()
                .uri("http://localhost:8080/wizards")
                .retrieve()
                .bodyToMono(Wizards.class)
                .block();
    }
    public void indexWizards(TextField FullNameField, ComboBox<String> PositionField, TextField DollarField, RadioButtonGroup<String> GenderRadio, ComboBox<String> SchoolBox, ComboBox<String> HouseBox) {
        if(index >= 0){
            Wizard currentWizard = wizards.get(index);

            FullNameField.setValue(currentWizard.getName());
            if(Objects.equals(currentWizard.getSex(), "m")){
                GenderRadio.setValue("Male");
            } else {
                GenderRadio.setValue("Female");
            }
            PositionField.setValue(currentWizard.getPosition());
            DollarField.setValue(String.valueOf(currentWizard.getMoney()));
            SchoolBox.setValue(currentWizard.getSchool());
            HouseBox.setValue(currentWizard.getHouse());
        } else {
            FullNameField.setValue("");
            GenderRadio.setValue("");
            PositionField.setValue("");
            DollarField.setValue("");
            SchoolBox.setValue("");
            HouseBox.setValue("");
        }
    }
}
