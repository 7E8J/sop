package com.je8j.sop_lab_07.View;

import com.je8j.sop_lab_07.ProductService.pojo.Product;
import com.je8j.sop_lab_07.ProductService.pojo.ProductList;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route("index1")
public class ProductView extends VerticalLayout {
    private ProductList productList;

    ComboBox<String> productListComboBox;
    TextField productName;
    NumberField productCost, productProfit, productPrice;
    Product selectedProduct;

    private final RabbitTemplate rabbitmq;

    @Autowired
    public ProductView(RabbitTemplate rabbitmq) {
        this.rabbitmq = rabbitmq;

        productListComboBox = new ComboBox<>();
        productListComboBox.setLabel("Product List");
        productListComboBox.setWidth("600px");
        add(productListComboBox);

        setComboBox();

        productListComboBox.addValueChangeListener(event -> {
            String selectedProductName = event.getValue();
            if (selectedProductName != null) {
                selectedProduct = productList.getModel().stream()
                        .filter(product -> product.getProductName().equals(selectedProductName))
                        .findFirst()
                        .orElse(null);

                if (selectedProduct != null) {
                    productName.setValue(selectedProduct.getProductName());
                    productCost.setValue(selectedProduct.getProductCost());
                    productProfit.setValue(selectedProduct.getProductProfit());
                    productPrice.setValue(selectedProduct.getProductPrice());
                }
            }
        });

        productName = new TextField();
        productName.setLabel("Product Name:");
        productName.setWidth("600px");
        add(productName);

        productCost = new NumberField();
        productCost.setLabel("Product Cost:");
        productCost.setWidth("600px");
        productCost.setValue(0.0);
        add(productCost);

        productProfit = new NumberField();
        productProfit.setLabel("Product Profit:");
        productProfit.setWidth("600px");
        productProfit.setValue(0.0);
        add(productProfit);

        productPrice = new NumberField();
        productPrice.setLabel("Product Price:");
        productPrice.setWidth("600px");
        productPrice.setValue(0.0);
        productPrice.setReadOnly(true);
        add(productPrice);

        Button addProduct = new Button("Add Product");
        Button updateProduct = new Button("Update Product");
        Button deleteProduct = new Button("Delete Product");
        Button clearProduct = new Button("Clear Product");
        HorizontalLayout hlayout = new HorizontalLayout();
        hlayout.add(addProduct, updateProduct, deleteProduct, clearProduct);
        add(hlayout);


        addProduct.addClickListener(event -> {
            addProduct();
            setComboBox();

            Notification noti = new Notification("ดำเนินการสำเร็จ", 500, Notification.Position.TOP_START);
            noti.open();
        });

        updateProduct.addClickListener(event ->{
            updateProduct();
            setComboBox();

            Notification noti = new Notification("ดำเนินการสำเร็จ", 500, Notification.Position.TOP_START);
            noti.open();
        });

        deleteProduct.addClickListener(event ->{
           deleteProduct();
            productName.setValue("");
            productCost.setValue(0.0);
            productProfit.setValue(0.0);
            productPrice.setValue(0.0);

            selectedProduct = null;
            setComboBox();

            Notification noti = new Notification("ดำเนินการสำเร็จ", 500, Notification.Position.TOP_START);
            noti.open();
        });

        clearProduct.addClickListener(event ->{
           productName.setValue("");
           productCost.setValue(0.0);
           productProfit.setValue(0.0);
           productPrice.setValue(0.0);

           selectedProduct = null;
        });

        productCost.addKeyPressListener(Key.ENTER, event ->{
            calculate();
        });

        productProfit.addKeyPressListener(Key.ENTER, event ->{
            calculate();

        });
    }

    public void setComboBox() {
        if (rabbitmq != null) {
            productList = fetch();

            if(productList != null) {
                List<Product> products = productList.getModel();

                List<String> productNames = products.stream()
                        .map(Product::getProductName)
                        .collect(Collectors.toList());

                productListComboBox.setItems(productNames);
            }
        }
    }

    public ProductList fetch() {
        assert rabbitmq != null;
        Object result = rabbitmq.convertSendAndReceive("ProductExchange", "getall", "");

        if (result instanceof ArrayList) {
            ArrayList<Product> productListData = (ArrayList<Product>) result;

            ProductList productList = new ProductList();
            productList.setModel(productListData);

            return productList;
        } else {
            return null;
        }

    }

    public void calculate() {
        double cost = productCost.getValue();
        double profit = productProfit.getValue();

        productPrice.setValue(WebClient.create()
                .get()
                .uri("http://localhost:8080/getPrice/"+cost+"/"+profit)
                .retrieve()
                .bodyToMono(Double.class)
                .block()
        );
    }

    public void addProduct() {
        Product product = new Product(
                productName.getValue(),
                productCost.getValue(),
                productProfit.getValue(),
                productPrice.getValue()
        );

        assert rabbitmq != null;
        rabbitmq.convertSendAndReceive("ProductExchange", "add", product);
    }

    public void updateProduct() {
        if (selectedProduct != null) {
                Product updatedProduct = new Product(
                        productName.getValue(),
                        productCost.getValue(),
                        productProfit.getValue(),
                        productPrice.getValue()
                );

                assert rabbitmq != null;
                rabbitmq.convertSendAndReceive("ProductExchange", "update", updatedProduct);
            }
    }

    public void deleteProduct() {
        assert rabbitmq != null;
        rabbitmq.convertSendAndReceive("ProductExchange", "delete", selectedProduct);
    }

}
