package com.example.lab03;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@RestController
public class CustomerController {
    private final List<Customer> customers = new LinkedList<>(Arrays.asList(
            new Customer("1010", "John", "Male", 25),
            new Customer("1018", "Peter", "Male", 24),
            new Customer("1019", "Sara", "Female", 23),
            new Customer("1110", "Rose", "Female", 23),
            new Customer("1001", "Emma", "Female", 30)
    ));


    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomer(){
        return customers;
    }

    @RequestMapping(value = "/customerbyid/{id}", method = RequestMethod.GET)
    public Customer getCustomerByID(@PathVariable String id){
        Customer customer_id = null;
        for (Customer customer : customers) {
            if (Objects.equals(customer.getID(), id)) {
                customer_id = customer;

                break;
            }
        }
        return customer_id;
    }

    @RequestMapping(value = "/customerbyname/{n}", method = RequestMethod.GET)
    public Customer getCustomerByName(@PathVariable String n){
        Customer customer = null;
        for(Customer c : customers) {
            if(Objects.equals(c.getName(), n)){
                customer = c;

                break;
            }
        }

        return customer;
    }

    @RequestMapping(value = "/customerDelByid/{id}", method = RequestMethod.DELETE)
    public boolean delCustomerByID(@PathVariable String id){
        boolean statement = false;
        Customer target = null;

        for (Customer customer : customers) {
            if (Objects.equals(customer.getID(), id)) {
                target = customer;
                statement = true;
                break;
            }
        }
        if (target != null) {
            customers.remove(target);
        }


        return statement;
    }

    @RequestMapping(value= "/customerDelByName/{n}", method = RequestMethod.DELETE)
    public boolean delCustomerByName(@PathVariable String n){
        boolean statement = false;
        Customer target = null;

        for (Customer customer : customers) {
            if (Objects.equals(customer.getName(), n)) {
                target = customer;
                statement = true;
                break;
            }
        }
        if (target != null) {
            customers.remove(target);
        }

        return statement;
    }

    @GetMapping(value = "/addCustomer")
    public boolean addCustomer(@RequestParam("id") String ID, @RequestParam("name") String n,@RequestParam("sex") String s,@RequestParam("age") int a){
        boolean statement = false;

        try{
            Customer n_cusomter = new Customer(ID, n, s, a);
            customers.add(n_cusomter);

            statement = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return statement;
    }

    @PostMapping(value = "/addCustomer2")
    public boolean addCustomer2(@RequestParam("id") String ID, @RequestParam("name") String n,@RequestParam("sex") String s,@RequestParam("age") int a){
        boolean statement = false;

        try{
            Customer n_cusomter = new Customer(ID, n, s, a);
            customers.add(n_cusomter);

            statement = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return statement;
    }
}
