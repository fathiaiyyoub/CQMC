/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cqmc;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fali8
 */
public class FarmDataEntryController implements Initializable {

    @FXML
    private Button returnToMainPageButton;
    @FXML
    private Button saveDataButton;
    @FXML
    private TextField farmNameTextField;
    @FXML
    private TextField farmAddressTextField;
    @FXML
    private TextField farmPhoneTextField;
    @FXML
    private Button cleaDataButton;
    
    
    
    
    /*
    ArrayList of Farm (Objects)
    declared as static to be acccssed in the MainPageController 
    without the need to instaintaite an abject of the Controller class
    i.e FarmDataEntryController
    */
    
    // ArrayList of Farm Objects
    static ArrayList<Farm> farmsArryList = new ArrayList<>();
    
    // ArrayList<String> of Object.getName()
    static ArrayList<String> farmNameArrayList = new ArrayList<>();
   
   
    
    @FXML
    private void returnToMainPageButtonHandler (ActionEvent event) throws Exception
    {
        Utility.changeScene(getClass(), event, "MainPage.FXML");
    }

    
    @FXML
    private void saveDataButtonHandler(ActionEvent event) 
    {
        boolean dataValidated = true;
        
        //data validation
        if(dataValidated == true)
        {
            if(farmNameTextField.getText().trim().length() == 0)
            {        
                JOptionPane.showMessageDialog(null, "Farm name cannot be blank");
                dataValidated = false;
                return; // to early exit the method.
                        // without a return statement, the logic
                        // reaches the object and stores an empty object 
                        // it also calls toString() with null values
            }
        }
        
        if(dataValidated == true)
        {
            if(farmAddressTextField.getText().trim().length() == 0)
            {        
                JOptionPane.showMessageDialog(null, "Farm address cannot be blank");
                dataValidated = false;
                return;
            }
        }
        
        if(dataValidated == true)
        {
            if(farmPhoneTextField.getText().trim().length() == 0)
            {        
                JOptionPane.showMessageDialog(null, "Farm phone number cannot be blank");
                dataValidated = false;
                return;
            }
        }
        
        String name = this.farmNameTextField.getText();
        String address = farmAddressTextField.getText();
        String phoneNumber = farmPhoneTextField.getText();
        
        // to avoid duplicate entries
        for(int i = 0; i < farmsArryList.size(); i++)
        {
            if(farmNameTextField.getText().equalsIgnoreCase(farmsArryList.get(i).getName()))
            {
                JOptionPane.showMessageDialog(null, "Erro!\nFarm already exists in our database.");
                farmNameTextField.setText("");
                return; 
            }
        }
        
        Farm aFarm = new Farm(name, address, phoneNumber);
        
        farmsArryList.add(aFarm); //adds the object to the ArrayList
        
        //please see controller 
        farmNameArrayList.add(aFarm.getName());
        
        // clears data fields automatically to maintain data integrity
        cleaDataButtonHandler(event);
    }
    
    
    @FXML
    private void cleaDataButtonHandler(ActionEvent event) 
    {
        // clears data optionally if button is clicked 
        this.farmNameTextField.setText("");
        this.farmAddressTextField.setText("");
        this.farmPhoneTextField.setText("");
        
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}

