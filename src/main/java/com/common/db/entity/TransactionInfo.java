package com.common.db.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Kirill Stoianov on 06/09/17.
 */

@Entity
@Table(name = "t_info")
public class TransactionInfo {

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    private long id;

//    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinColumn(name = "company")
//    private Company company;

    @Column(name = "company")
    private long company;

    @Column(name = "action")
    private String action;

    @Column(name = "value")
    private double value;

    @Column(name = "balance")
    private double balance;

    @Column(name = "status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompany() {
        return company;
    }

    public void setCompany(long company) {
        this.company = company;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "id=" + id +
                ", company=" + company +
                ", action='" + action + '\'' +
                ", value='" + value + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}
