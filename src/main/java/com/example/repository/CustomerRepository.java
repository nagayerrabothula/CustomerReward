package com.example.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.models.Customer;

@Repository
public interface CustomerRepository extends JpaSpecificationExecutor<Customer> {

}
