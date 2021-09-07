/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cqmc;

/**
 *
 * @author fali8
 * 
 */

/*
data filed and methods are declared as static as we do not need to create objects of the class. 
we only need to store milkavail variable in separate memory location 
*/
public class Silo 
{
    public static int availableForDelivery;

    // default constructor
    public Silo() 
    {
    }
    
    // parameterised constructor
    public Silo(int availableForDelivery) 
    {
        Silo.availableForDelivery = availableForDelivery;
    } 

    public static int getAvailableForDelivery() {
        return availableForDelivery;
    }

    public static void setAvailableForDelivery(int availableForDelivery) {
        Silo.availableForDelivery = availableForDelivery;
    }
   
}
