package com.example.demo.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {
    private List<OperationDto> operations;
    private double totalOperations;
    private double cashBalance;
    private double smxCardBalance;
    private double creditOrDebitCardBalance;
    private double totalBalance;
    private Date startQuery;
    private Date endQuery;



    public List<OperationDto> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationDto> operations) {
        this.operations = operations;
    }


    public void setTotalOperations(Integer totalOperations) {
        this.totalOperations = totalOperations;
    }



    public void setSmxCardBalance(double smxCardBalance) {
        this.smxCardBalance = smxCardBalance;
    }

    public double getTotalOperations() {
        return totalOperations;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public double getSmxCardBalance() {
        return smxCardBalance;
    }

    public double getCreditOrDebitCardBalance() {
        return creditOrDebitCardBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public Date getStartQuery() {
        return startQuery;
    }

    public Date getEndQuery() {
        return endQuery;
    }

    public void setCreditOrDebitCardBalance(double creditOrDebitCardBalance) {
        this.creditOrDebitCardBalance = creditOrDebitCardBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setStartQuery(Date startQuery) {
        this.startQuery = startQuery;
    }

    public void setEndQuery(Date endQuery) {
        this.endQuery = endQuery;
    }

    public void setCashBalance(double i) {
        this.cashBalance = i;
    }
}
