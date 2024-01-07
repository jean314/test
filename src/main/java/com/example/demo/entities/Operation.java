package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "operations")
public class Operation {
    public Operation(Integer operationNumber, Integer idLocal, Integer terminal, OperationType operationType, Double totalAmount, Double cashPayment, Double creditOrDebitCardPayment, Double smxCardPayment, Double change, Boolean proccesedCorrectly, String details, String generatedSxaracFileNumber) {
        this.operationNumber = operationNumber;
        this.idLocal = idLocal;
        this.terminal = terminal;
        this.operationType = operationType;
        this.totalAmount = totalAmount;
        this.cashPayment = cashPayment;
        this.creditOrDebitCardPayment = creditOrDebitCardPayment;
        this.smxCardPayment = smxCardPayment;
        this.change = change;
        this.proccesedCorrectly = proccesedCorrectly;
        this.details = details;
        this.generatedSxaracFileNumber = generatedSxaracFileNumber;
    }



    public Operation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private long id;

    @Column(name = "operation_number")
    private Integer operationNumber;

    @Column(name="terminal")
    private Integer terminal;

    @Column(name="id_local")
    private Integer idLocal;

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datetime")
    private Date dateTime = new Date();

    @Column(name = "cash_payment")
    private Double cashPayment;

    @Column(name = "credit_or_debit_card_payment")
    private Double creditOrDebitCardPayment;

    @Column(name = "smx_card_payment")
    private Double smxCardPayment;

    @Column(name = "change")
    private Double change;

    @Column(name = "proccesed_correctly")
    private Boolean proccesedCorrectly;

    @Column(name = "details")
    private String details;

    @Column(name = "generated_sxarac_file_number")
    private String generatedSxaracFileNumber;

    public String getDetails() {
        return details.replace("\\", "");
    }

    public long getId() {
        return id;
    }

    public Integer getOperationNumber() {
        return operationNumber;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public Double getCashPayment() {
        return cashPayment;
    }

    public Double getCreditOrDebitCardPayment() {
        return creditOrDebitCardPayment;
    }

    public Double getSmxCardPayment() {
        return smxCardPayment;
    }

    public Double getChange() {
        return change;
    }

    public Boolean getProccesedCorrectly() {
        return proccesedCorrectly;
    }

    public String getGeneratedSxaracFileNumber() {
        return generatedSxaracFileNumber;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
