package com.example.demo.responses;

import com.example.demo.entities.OperationType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class OperationDto {

    @NotEmpty
    private Integer operationNumber;
    @NotEmpty
    private Integer terminal;

    @NotEmpty
    private Integer idLocal;
    @NotEmpty
    private OperationType operationType;
    @NotEmpty
    private Double totalAmount;
    @NotEmpty
    private Date dateTime;

    @NotEmpty
    private Double cashPayment;

    @NotEmpty
    private Double creditOrDebitCardPayment;

    @NotEmpty
    private Double smxCardPayment;

    @NotEmpty
    private Double change;

    @NotEmpty
    private Boolean proccesedCorrectly;

    @NotEmpty
    private String details;

    @NotEmpty
    private String generatedSxaracFileNumber;
}
