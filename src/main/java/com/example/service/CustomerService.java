package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public List<Customer> getCustomerAll() {
        return customerRepository.findAll();
    }
    
    @Override
    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
    
    @Override
    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }
    
    @Override
    public Customer updateCustomer(Customer customer) {
        Integer customerId = customer.getId();
        customerRepository.findById(customerId).orElse(null);
        Customer updatedCustomer = customerRepository.save(customer);
        return updatedCustomer;
    }
}