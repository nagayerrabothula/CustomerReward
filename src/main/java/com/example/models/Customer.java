package com.example.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer extends Rewards{
    @Id
    @GeneratedValue
    private long  customerId;
    
    private String name;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transactions> transactions;
    
   
    @JsonInclude
    @Transient
    private Double totalPurchases;

    public Customer() {
        super();
    }

    public Customer(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }
    @JsonGetter("rewardPoints")
    public Long getRewardPoints() {
        if (transactions == null || transactions.isEmpty()) {
            return 0l;   
        }
        return transactions.stream().map(x -> x.getPoints().intValue()).reduce(0, (a, b) -> a + b).longValue();
    }

    public Double getTotalPurchases() {
        if (transactions == null || transactions.isEmpty())
            return 0d;

        return transactions.stream().map(x -> x.getTotal().doubleValue()).reduce(0d, (a, b) -> a + b).doubleValue();
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + ", transactions=" + transactions
                + ", rewardPoints=" + rewardPoints + ", totalPurchases=" + totalPurchases + "]";
    }

    
}
