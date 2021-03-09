package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.example.models.Customer;
import com.example.models.Transactions;
import com.example.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

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
        customerRepository.findById(customerId).orElseThrow(() -> new Exception());
        Customer updatedCustomer = customerRepository.save(customer);
        return updatedCustomer;
    }

    @Override
    public Customer updateCustomerTransaction(Transactions transaction, Long customerId) {
        Customer customer = customerRepository.findById(customerId);
        Set<Transactions> transactions = customer.getTransactions();
        transactions.add(transaction);
        customer.setTransactions(transactions);
        Customer updatedCustomerTransactionInfo = customerRepository.save(customer);
        return updatedCustomerTransactionInfo;
    }

}
