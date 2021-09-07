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
public class DairyDataEntryController implements Initializable {

    /*
    ArrayList of Dairy (Objects)
    declared as static to be acccssed in the MainPageController 
    without the need to instaintaite an abject of the Controller class
    i.e FarmDataEntryController
    */
    
    
    @FXML
    private Button returnToMainPageButton;
    @FXML
    private Button saveDataButton;
    @FXML
    private Button cleaDataButton;
    @FXML
    private TextField dairyEmailTextField;
    @FXML
    private TextField dairyNameTextField;
    @FXML
    private TextField dairyAddressTextField;
    @FXML
    private TextField dairyPhoneTextField;
    
    
    // ArrayList of Dairy Objects
    static ArrayList<Dairy> dairiesArrayList = new ArrayList<>();
    
    // ArrayList<String> of Object.getName()
    static ArrayList<String> dairyNameArrayList = new ArrayList<>();
   
    @FXML
    private void returnToMainPageButtonHandler(ActionEvent event) throws Exception
    {
        Utility.changeScene(getClass(), event, "MainPage.FXML");
    }

    @FXML
    private void saveDataButtonHandler(ActionEvent event) 
    {
        boolean dataValidated = true;
        
        if(dataValidated == true)
        {
            if(dairyNameTextField.getText().trim().length() == 0)
            {        
                JOptionPane.showMessageDialog(null, "Farm name cannot be blank");
                dataValidated = false;
                return; // to early exit the method
                        // without a return statement, the logic
                        // reaches the object and stores an empty object 
                        // it also calls toString() with null values
            }
        }
        
        if(dataValidated == true)
        {
            if(dairyAddressTextField.getText().trim().length() == 0)
            {        
                JOptionPane.showMessageDialog(null, "Farm name cannot be blank");
                dataValidated = false;
                return; // to early exit the method
                        // without a return statement, the logic
                        // reaches the object and stores an empty object 
                        // it also calls toString() with null values
            }
        }
        
        if(dataValidated == true)
        {
            if(dairyPhoneTextField.getText().trim().length() == 0)
            {        
                JOptionPane.showMessageDialog(null, "Farm name cannot be blank");
                dataValidated = false;
                return; // to early exit the method
                        // without a return statement, the logic
                        // reaches the object and stores an empty object 
                        // it also calls toString() with null values
            }
        }
        
        if(dataValidated == true)
        {
            if(dairyEmailTextField.getText().trim().length() == 0)
            {        
                JOptionPane.showMessageDialog(null, "Farm name cannot be blank");
                dataValidated = false;
                return; // to early exit the method
                        // without a return statement, the logic
                        // reaches the object and stores an empty object 
                        // it also calls toString() with null values
            }
        }
        
        String name = this.dairyNameTextField.getText();
        String address = this.dairyAddressTextField.getText();
        String phoneNumber = this.dairyPhoneTextField.getText();
        String email = this.dairyEmailTextField.getText();
        
        Dairy aDairy = new Dairy(name, address, phoneNumber, email);
        dairiesArrayList.add(aDairy);
        
        //choiceBox of dairies
        dairyNameArrayList.add(aDairy.getName());
        
        cleaDataButtonHandler(event);
        
    }

    @FXML
    private void cleaDataButtonHandler(ActionEvent event) 
    {
        // clears data optionally if button is clicked 
        this.dairyNameTextField.setText("");
        this.dairyAddressTextField.setText("");
        this.dairyPhoneTextField.setText("");
        this.dairyEmailTextField.setText("");

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
