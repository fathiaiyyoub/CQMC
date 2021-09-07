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

/*
Justification: 

Since the CQMC maintains business relatinship with different business partners,
it more logical to aggregate all in one abstract class as they share similar attributes
*/
public abstract class PartnerEntity 
{
    
    private String name;
    private String address;
    private String phoneNumner; // most suitable data type for phone numbers

    // default constructor
    public PartnerEntity() {
    }

    // parameterised constructor
    public PartnerEntity(String name, String address, String phoneNumner) {
        this.name = name;
        this.address = address;
        this.phoneNumner = phoneNumner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumner() {
        return phoneNumner;
    }

    public void setPhoneNumner(String phoneNumner) {
        this.phoneNumner = phoneNumner;
    }

    @Override
    public String toString() {
        
        // will compensate on that with the header of the diaplyArea header
        return "Business Name: " + name + " || Address: " + address + " || Phone Numner: " + phoneNumner;
    }
}
