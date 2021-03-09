package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.models.Transactions;
import com.example.models.Customer;
import com.example.service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    /* Used to create a customer */
    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        com.example.models.Customer newCustomer = new Customer();
        newCustomer.setName(customer.getName());
        Customer createdCustomer = service.createCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{customerId}")
                .buildAndExpand(createdCustomer.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /* This is used to update a customer */
    @PutMapping("/customers/{id}")
    public ResponseEntity<com.example.models.Customer> updateCustomerTransaction(@RequestBody Customer updatedcustomer)
            throws Exception {
        // updated customer with customerInformation
        if (updatedcustomer.getId() != null) {
            Customer updatedCustomer = service.updateCustomer(updatedcustomer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            throw new Exception();
        }
    }

    /* Used to add a transaction to customer */
    @PostMapping("/customer/{id}/transaction")
    public ResponseEntity<Customer> createCustomerTransaction(@PathVariable Long customerId,
            @RequestBody Transactions transaction) throws Exception {
        // updated customer with a new transaction
        if (customerId != null) {
            Customer updatedCustomerTransaction  = service.updateCustomerTransaction(transaction, customerId);
            return new ResponseEntity<>(updatedCustomerTransaction, HttpStatus.OK);
        } else {
            throw new Exception();
        }
    }

    /* Used to retrieve a customer based on Id */
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
        Customer customer = service.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    /* Used to retrieve all the customers */
    @GetMapping("/customers")
    public List<Customer> findCustomerAll() {
        return service.getCustomerAll();
    }

}