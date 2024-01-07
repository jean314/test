package com.example.demo.requests;

import com.example.demo.entities.OperationType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OperationRequest {

    @NotEmpty
    private Integer operationNumber;
    @NotEmpty
    private OperationType operationType;
    @NotEmpty
    private Integer terminal;
    @NotEmpty
    private Integer idLocal;
    @NotEmpty
    private Double totalAmount;
    @NotEmpty
    private Boolean proccessedCorrectly;
    @NotEmpty
    private Object objectDetails;
    @NotEmpty
    private String details;
    @NotEmpty
    private Double cashPayment;
    @NotEmpty
    private Double creditOrDebitCardPayment;
    @NotEmpty
    private Double smxCardPayment;
    @NotEmpty
    private Double change;
    @NotEmpty
    private String generatedSxaracFileNumber;

    public Integer getOperationNumber() {
        return operationNumber;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Boolean getProccessedCorrectly() {
        return proccessedCorrectly;
    }

    public Object getObjectDetails() {
        return objectDetails;
    }

    public Double getCashPayment() {
        return cashPayment;
    }

    public String getDetailsToString() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String convertedObject = objectMapper.writeValueAsString( this.objectDetails);
            System.out.println("Objeto convertido a string: \n"+convertedObject);
            return  convertedObject;
        }catch(Exception e){
            System.out.println("Error al convertir JSON a String: "+ e.getMessage());
            return "";
        }
    }
    public void setDetails(String details) {
        this.details = details;
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

    public String getGeneratedSxaracFileNumber() {
        return generatedSxaracFileNumber;
    }

    public String getDetails() {
        return details;
    }

    public Integer getIdLocal() {
        return idLocal;
    }
}
