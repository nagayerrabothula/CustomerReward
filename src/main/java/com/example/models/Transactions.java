package com.example.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transactions extends Rewards {
    @Id
    @GeneratedValue
    private Long transactionId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Customer customer;

    private Double total;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date saveDate;

    public Transactions() {
        super();
    }

    public Transactions(Long id, Customer customer, Double total, String description, Date date) {
        super();
        this.transactionId = id;
        this.customer = customer;
        this.total = total;
        this.saveDate = date;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    @Override
    public String toString() {
        return "Transactions [transactionId=" + transactionId + ", customer=" + customer + ", total=" + total
                + ", saveDate=" + saveDate + "]";
    }

    @Override
    public Long getPoints() {
        // TODO Auto-generated method stub
        return null;
    }


}
