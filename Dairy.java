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
public class Dairy extends PartnerEntity {

    private String email;

    public Dairy() {
    }
    
    public Dairy(String name, String address, String phoneNumner, String email) {
        super(name, address, phoneNumner);
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @Override
    public String toString() {
        return super.toString() + " || Email: " + email;
    }
    
}
