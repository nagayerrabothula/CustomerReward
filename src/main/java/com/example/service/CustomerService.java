package com.example.service;

import java.util.List;

import com.example.models.Customer;
import com.example.models.Transactions;

/**
 * Service for dealing with Customer entities
 */
public interface CustomerService {

    List<Customer> getCustomerAll();

    Customer getCustomerById(Integer customerId);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer updateCustomerTransaction(Transactions transaction, Long customerId);

}
