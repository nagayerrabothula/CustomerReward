package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.service.springbootrewards.model.Customer;
import com.example.service.springbootrewards.model.MyTransaction;

@RestController
public class CustomerController {

    
    @Autowired
    private CustomerService service;
    
    
    @PostMapping("/customer")
    public ResponseEntity<List<Customer>> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setName(customer.getName());
        Customer customer = service.createClaim();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{customerId}")
                .buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        if (customer.getId() != null) {
            Customer updatedCustomer  = service.updateCustomer();
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }else {
            throw new Exception();  
        }
    }
    
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id ) {
        Customer customer = service.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
    
    @GetMapping("/customers")
    public List<Customer> findCustomerAll() {
        return service.getCustomerAll();
    }
    
}