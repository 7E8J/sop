package com.example.sop_lab_04;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

import org.springframework.web.reactive.function.client.WebClient;

@Route("index1")
public class MathView extends VerticalLayout {
    private final NumberField tf_n1;
    private final NumberField tf_n2;
    private final NumberField tf_ans;

    public MathView() {
        tf_n1 = new NumberField("Number 1");
        tf_n2 = new NumberField("Number 2");
        tf_ans = new NumberField("Answer");

        Button btnPlus = new Button("+");
        Button btnMinus = new Button("-");
        Button btnMultiply = new Button("X");
        Button btnDivind = new Button("/");
        Button btnMod = new Button("Mod");
        Button btnMax = new Button("Max");

        HorizontalLayout hL = new HorizontalLayout();
        hL.add(btnPlus, btnMinus, btnMultiply, btnDivind, btnMod, btnMax);

        FormLayout fL1 = new FormLayout();
        FormLayout fL2 = new FormLayout();
        FormLayout fL3 = new FormLayout();
        FormLayout fL4 = new FormLayout();

        fL1.add(tf_n1);
        fL2.add(tf_n2);
        fL3.add(hL);
        fL4.add(tf_ans);

        this.add(fL1);
        this.add(fL2);
        this.add("Operator");
        this.add(fL3);
        this.add(fL4);

        btnPlus.addClickListener(event ->{
            double num1 = tf_n1.getValue();
            double num2 = tf_n2.getValue();
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/plus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            assert out != null;
            tf_ans.setValue(Double.parseDouble(out));
        });
        btnMinus.addClickListener(event ->{
            double num1 = tf_n1.getValue();
            double num2 = tf_n2.getValue();
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/minus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            assert out != null;
            tf_ans.setValue(Double.parseDouble(out));
        });
        btnMultiply.addClickListener(event ->{
            double num1 = tf_n1.getValue();
            double num2 = tf_n2.getValue();
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/multi/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            assert out != null;
            tf_ans.setValue(Double.parseDouble(out));
        });
        btnDivind.addClickListener(event ->{
            double num1 = tf_n1.getValue();
            double num2 = tf_n2.getValue();
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/divide/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            assert out != null;
            tf_ans.setValue(Double.parseDouble(out));
        });
        btnMod.addClickListener(event ->{
            double num1 = tf_n1.getValue();
            double num2 = tf_n2.getValue();
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/mod/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            assert out != null;
            tf_ans.setValue(Double.parseDouble(out));
        });
        btnMax.addClickListener(event ->{
            double num1 = tf_n1.getValue();
            double num2 = tf_n2.getValue();
            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/max?num1="+num1+"&num2="+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            assert out != null;
            tf_ans.setValue(Double.parseDouble(out));
        });
    }
}
