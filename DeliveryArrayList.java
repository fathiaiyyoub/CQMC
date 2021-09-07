/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cqmc;

import java.util.ArrayList;

/**
 *
 * @author fali8
 */
public class DeliveryArrayList {
    
   public static ArrayList<Delivery> deliveryArrayList = new ArrayList<>();
   // no need for constructor or getters and setters as will be using built-in methods
}

/*
why icreated this? 
The deliveryArrayList was delacred and initialised in the MainPageConteoller. Everytime the user switches 
scences, the MainPageConteoller gets initialsed and delveries cannot be displayed because the arrayList 
has been resetted (empty). Thus, i have to store the (Delivery (Object)) in a data structure in a momory location where it 
cannot be accessed and/or mutated by other classes :-)
*/
