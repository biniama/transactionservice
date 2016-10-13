package com.n26.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Entity class where the elements of Transactions are defined.
 *
 * @author Biniam Asnake.
 */
@Entity
public class Transaction {

    @Id
    @Column
    private Long id;

    @Column
    @NotNull(message = "error.transaction.type.null")
    private String type;

    @Column
    @NotNull(message = "error.transaction.amount.null")
    private double amount;

    @Column
    private Long parentId;

    // default constructor is required for rendering/casting
    public Transaction() {
    }

    // constructor with only type and amount specified
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.parentId = null;
    }

    // constructor with all elements
    public Transaction(String type, double amount, Long parentId) {
        this.type = type;
        this.amount = amount;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return String.format(
                "Transaction [id=%d, type='%s', amount='%,.2f', parent='%s']",
                getId(), getType(), getAmount(), getParentId() != null? getParentId() : "not specified");
    }
}
