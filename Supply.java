/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cqmc;

/**
 *
 * @author fali8
 */
import java.time.*;

// Deliveries sent/to be sent to Dairy (Class)

public class Supply {
    
    private LocalDate supplyDate;
    private int quantity;
    private String tankerRegNumber;
    private String dispatchedTo;

    public Supply() {
    }

    public Supply(int year, int month, int day, int quantity, String tankerRegNumber, String dispatchedTo) {
        this.supplyDate = LocalDate.of(year, month, day);
        this.quantity = quantity;
        this.tankerRegNumber = tankerRegNumber;
        this.dispatchedTo = dispatchedTo;
    }

    public LocalDate getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(LocalDate supplyDate) {
        this.supplyDate = supplyDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTankerRegNumber() {
        return tankerRegNumber;
    }

    public void setTankerRegNumber(String tankerRegNumber) {
        this.tankerRegNumber = tankerRegNumber;
    }

    public String getDispatchedTo() {
        return dispatchedTo;
    }

    public void setDispatchedTo(String dispatchedTo) {
        this.dispatchedTo = dispatchedTo;
    }

    @Override
    public String toString() {
        return "Delivery Date: " + supplyDate + " || Quantity: " + quantity + " || TankerRegNumber: " + tankerRegNumber
                + " || Dispacted To: " + dispatchedTo;
    }
   
}
