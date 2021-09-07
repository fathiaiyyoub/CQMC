/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cqmc;

/**
 *
 * @author Fathi AIYYOUB
 */

import java.time.*;

// Deliveries received to be sotred and chilled in silos

public class Delivery {
    
    private LocalDate deliveryDate;
    private int quantity;
    private String testResult;
    private String deliveryId;
    private String sourceFarm;
   
    public Delivery() {
    }

    
    public Delivery(int year, int month, int day, int quantity, String testResult, String deliveryId, String sourceFarm) {
        this.deliveryDate = LocalDate.of(year, month, day);
        this.quantity = quantity;
        this.testResult = testResult;
        this.deliveryId = deliveryId;
        this.sourceFarm = sourceFarm;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getSourceFarm() {
        return sourceFarm;
    }

    public void setSourceFarm(String sourceFarm) {
        this.sourceFarm = sourceFarm;
    }
    
   
    @Override
    public String toString() {
        return "Delivery Date: " + deliveryDate + " || Quantity: " + quantity + " || Delivery ID: " 
                + deliveryId + " || Source Farm: " + sourceFarm + " || Test Result: " + testResult;
    }
    
}
