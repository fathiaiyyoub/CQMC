/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cqmc;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;



/**
 *
 * @author fali8
 */
public class MainPageController implements Initializable {
   
    @FXML
    private Button addFarmButton;
    @FXML
    private Button displayFamrsButton;
    @FXML
    private Button addDairyButton;
    @FXML
    private Button displayDairyButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextArea displayArea;
    @FXML
    private ChoiceBox farmChoiceMenu;
    @FXML
    private RadioButton rejectedRadioButton;
    @FXML
    private RadioButton acceptedRadioButton;
    @FXML
    private Slider deliveryAmountSlider;
    @FXML
    private ChoiceBox dairyChoiceMenu;
    @FXML
    private Slider supplyAmountSlider;
    @FXML
    private Button addDeliveryButton;
    @FXML
    private DatePicker supplyDate;
    @FXML
    private DatePicker deliveryDate;
    @FXML
    private TextField deliveryIdTextField;
    @FXML
    public Label milkAvailableLabel;
    @FXML
    private Button addSupplyButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button resetDeliveryButton;
    @FXML
    private Button resetSupplyButton;
    @FXML
    private TextField tankerTextField;
    @FXML
    private Button diaplayDeliveriesButton;
    @FXML
    private Button displaySuppliesButton;
    @FXML
    private Label dateLabel;

    // The below will be used to access name only for the options menue (choiceBox)
    // it look neater than displaying the toString() method
    ObservableList<String> farmNameOptionList = FXCollections.observableArrayList(FarmDataEntryController.farmNameArrayList);
    ObservableList<String> dairyNameOptionList = FXCollections.observableArrayList(DairyDataEntryController.dairyNameArrayList);
    
    public MainPageController() 
    {
        
    }
   
    @FXML
    private void addFarmButtonHandler(ActionEvent event) throws Exception
    {
        // switches scences
        Utility.changeScene(getClass(), event, "FarmDataEntry.fxml");
    }

    @FXML
    private void displayFamrsButtonHandler(ActionEvent event) 
    {
        this.displayArea.setText(""); // to diaplay distinct entries on the textArea
        // diaplay all Farm objects to the displayArea in the MainPage
        FarmDataEntryController.farmsArryList.forEach((x) -> {
        displayArea.appendText(x.toString() + "\n");});
    }

    @FXML
    private void addDairyButtonHandler(ActionEvent event) throws Exception
    {
        Utility.changeScene(getClass(), event, "DairyDataEntry.FXML");
    }

    @FXML
    private void displayDairyButtonHandler(ActionEvent event) 
    {
        this.displayArea.setText(""); // to diaplay distinct entries only
        // diaplay all Farm objects to the displayArea in the MainPage
        DairyDataEntryController.dairiesArrayList.forEach((x) -> {
        displayArea.appendText(x.toString() + "\n");});
    }

    @FXML
    private void addDeliveryButtonHandler(ActionEvent event) 
    {   // receiving milk from farms. handled in if() stateents and return statement;
        
        // Delivery Object instantiation        
        Delivery aDelivery = new Delivery();
        
        
        //this section handles radioButton selection 
        String accepteTestResult = "Accepted";
        String rejectedTestResult = "Rejected";
        
        // if both buttons are clicked 
        if(this.acceptedRadioButton.isSelected() && this.rejectedRadioButton.isSelected())
        {
            JOptionPane.showMessageDialog(null, "error!\nYou must specify ACCEPTED or REJECTED");
            this.rejectedRadioButton.setSelected(false);
            this.acceptedRadioButton.setSelected(false);
            return; //to prevent the logic from transferring to the next block
            
        }
        // if neither button is clicked
        else if(!this.acceptedRadioButton.isSelected() && !this.rejectedRadioButton.isSelected())
        {
            JOptionPane.showMessageDialog(null, "error!\nYou must specify either ACCEPTED or REJECTED");
            return;
        }
        // everything is good
        else if(this.acceptedRadioButton.isSelected())
        {
            aDelivery.setTestResult(accepteTestResult);
        }
        else 
        {
            aDelivery.setTestResult(rejectedTestResult);
        }
                
        //this section handles quantity delivered (slider)
        //gets and validates the value from the slider
        int deliveryQuantity = (int) this.deliveryAmountSlider.getValue();
        if(deliveryQuantity == 0)
        {
            JOptionPane.showMessageDialog(null, "error!\nYou must specify milk QUANTITY");
            return;
        }
        else if(aDelivery.getTestResult().equals(accepteTestResult))
        {
            aDelivery.setQuantity(deliveryQuantity);
            int currentAvailable = Silo.getAvailableForDelivery();
            int increasedAvailable = currentAvailable += deliveryQuantity;
            Silo.setAvailableForDelivery(increasedAvailable);
        }
        else if(aDelivery.getTestResult().equals(rejectedTestResult))
        {
            aDelivery.setQuantity(deliveryQuantity);
        }    
       
        // delivery date section (DatePicker)
        LocalDate dateOfDelivery = this.deliveryDate.getValue();
        
        if(dateOfDelivery == null)
        {
            JOptionPane.showMessageDialog(null, "error!\nYou must specify a date");
            return;
        }
        else 
        {
            aDelivery.setDeliveryDate(dateOfDelivery);
        }
               
        // this section handles the source of milk delivered
        String sourceOfMilk = (String) this.farmChoiceMenu.getValue();
        
        if(farmChoiceMenu.getValue() == null || farmChoiceMenu.getValue() == "Select Farm")
        {
            JOptionPane.showMessageDialog(null, "error!\nPlease select a Farm from the drowpdown menu!");
            return;
        }
        else 
        {
            aDelivery.setSourceFarm(sourceOfMilk);
        }
               
        // this section autoincrements the delivery ID 
        // to ensure data integrity and consistency     
        //this.deliveryIdTextField.setText(String.valueOf(Silo.getAvailableForDelivery()));
        int deliveryId = DeliveryArrayList.deliveryArrayList.size() + 1;
        this.deliveryIdTextField.setText(String.valueOf(deliveryId));
        aDelivery.setDeliveryId("Del." + deliveryId);
        
        this.deliveryIdTextField.setText(String.valueOf(aDelivery.getDeliveryId()));
                
        milkAvailableLabel.setText(String.valueOf(Silo.getAvailableForDelivery()));
        supplyAmountSlider.setMax(Silo.getAvailableForDelivery());
        // all instance fields have been assigned values
        // we now need to add it to the ArrayList 
        DeliveryArrayList.deliveryArrayList.add(aDelivery);
        
        // resets all fields to take a new delivery, if any
        resetDeliveryButtonHandler(event);
        
    } // end addDeliveryButtonHandler method
    
    @FXML
    private void resetDeliveryButtonHandler(ActionEvent event) 
    {
        this.acceptedRadioButton.setSelected(false);
        this.rejectedRadioButton.setSelected(false);
        this.farmChoiceMenu.setValue("Select Farm");
        this.deliveryAmountSlider.setValue(0);
        this.deliveryDate.setValue(null); 
    }
    
    @FXML
    private void diaplayDeliveriesButtonHandler(ActionEvent event) 
    {
        // prints all delivries to the textArea
        this.displayArea.setText("");
        this.displayArea.setEditable(false);
        DeliveryArrayList.deliveryArrayList.forEach((x) -> {
        displayArea.appendText(x.toString() + "\n");});
    }

    @FXML
    private void addSupplyButtonHandler(ActionEvent event)      
    { // handles milk being sent to Dairies..
        
        // Supply Object instantiation
        Supply aSupply = new Supply();
        
        // supply slider handler
        int supplyQuantity = (int) this.supplyAmountSlider.getValue();
        if(supplyQuantity == 0)
        {
            JOptionPane.showMessageDialog(null, "Error\nYou must specify a milk quantity!");
            return;
        }
        
        // this section is reduntant but i have elected to include in case future system updates are required
        // please refer to the initialize method 
        else if(supplyQuantity > Silo.getAvailableForDelivery())
        {
            JOptionPane.showMessageDialog(null, "Error\nTHe milk in our silos is\nLESS than rhe required amount!");
            return;
        }
        else
        {            
            aSupply.setQuantity(supplyQuantity);           
        }
        
        // Supplied to dairy selection
        //String dispatchedTo = (String) this.dairyChoiceMenu.getValue();
        if(dairyChoiceMenu.getValue() == null || dairyChoiceMenu.getValue() == "Select Dairy")
        {
            JOptionPane.showMessageDialog(null, "Error\nPlease select a Dairy from the drowpdown menu!");
            return;          
        }
        else
        {
            String dispatchedTo = (String) this.dairyChoiceMenu.getValue(); 
            aSupply.setDispatchedTo(dispatchedTo);
        }
        
        // gets and verify the date from DatePicker
        LocalDate supplyOnDate = this.supplyDate.getValue();
        if(supplyOnDate == null)
        {
            JOptionPane.showMessageDialog(null, "Error\nPlease specify a date!");
            return;
        }
        else
        {
            aSupply.setSupplyDate(supplyOnDate);
        }        
        
        // get the tanker number from textField
        if(tankerTextField.getText().trim().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Tanker Number cannot be blank");
            return;
        }
        
        else if(tankerTextField.getText().trim().length() > 6)
        {
            JOptionPane.showMessageDialog(null, "Error\nTanker resigtration Number length cannot be more than 6 ");
            return;
        }
        else
        {            
            aSupply.setTankerRegNumber(this.tankerTextField.getText());            
        }
        
        // only deducted after all errors have been checked. othwrwise, it will deduct even 
        // error messages have been displayed to the user -- Thanks for testing :-)
        int currentBeforeSupply = Silo.getAvailableForDelivery();
        int currentAfterSupply = currentBeforeSupply -= supplyQuantity;
        Silo.setAvailableForDelivery(currentAfterSupply);
        supplyAmountSlider.setMax(Silo.getAvailableForDelivery());
        milkAvailableLabel.setText(String.valueOf(Silo.getAvailableForDelivery()));
        
        // adds the object to the ArrayList
        SupplyArrayList.supplyArrayList.add(aSupply);        
        resetSupplyButtonHandler(event);
        
    }// addSupplyButtonHandler

    @FXML
    private void resetSupplyButtonHandler(ActionEvent event) 
    {
        // resets data 
        this.tankerTextField.setText("");
        this.dairyChoiceMenu.setValue("Select Dairy");
        this.supplyAmountSlider.setValue(0);
        this.supplyDate.setValue(null); 
    }

    @FXML
    private void displaySuppliesButtonHandler(ActionEvent event) 
    {
        // prints all supplies to the textArea
        this.displayArea.setText("");
        this.displayArea.setEditable(false);
        SupplyArrayList.supplyArrayList.forEach((x) -> {
        displayArea.appendText(x.toString() + "\n");});
    }
    
    @FXML
    private void searchButtonHandler(ActionEvent event) 
    {
        displayArea.setText("");
        
        Object[] options = {"Farm", "Dairy", "Delivery", "Supply"};
        Object selectionObject;
        String userSelection;

        selectionObject = JOptionPane.showInputDialog(null, "What are you trying to find?", "Search Area", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if(selectionObject!=null)
        {
            userSelection = selectionObject.toString();
        }
        
        else
        {
            return;
        }
      
        boolean found = false;
        
        String searchKey; 
       
        while(true) 
        {     
            searchKey = JOptionPane.showInputDialog(null, "What do you want to find?\nPlease ener relevant data");
            if(searchKey == null)
            {// if user clicked cancel 
                return;
            }
            else if(searchKey.compareTo("") == 0)
            {// if user clicked OK without typing anything in the input field                  
               JOptionPane.showMessageDialog(null, "This field cannot be blank");
               continue;
            }   
           break; // exits the loop
        }// end while loop
        
        while(!found)
        {
            if("Farm".equals(userSelection)) // Search in the farmsArraylist
            {
                for(int index = 0; index < FarmDataEntryController.farmsArryList.size(); index ++)
                {           
                    if(FarmDataEntryController.farmsArryList.get(index).toString().toLowerCase().contains(searchKey.toLowerCase()))
                    {                   
                        displayArea.appendText(FarmDataEntryController.farmsArryList.get(index).toString() + "\n");    
                    }
                } 
                break;
            }// end farms if block
            
            if("Dairy".equals(userSelection)) // Search in the dairiesArraylis
            {
                for(int index = 0; index < DairyDataEntryController.dairiesArrayList.size(); index ++)
                {           
                    if(DairyDataEntryController.dairiesArrayList.get(index).toString().toLowerCase().contains(searchKey.toLowerCase()))
                    {
                        displayArea.appendText(DairyDataEntryController.dairiesArrayList.get(index).toString() + "\n");
                    } 
                } 
                break;
            }// end dairies if block
            
            if("Delivery".equals(userSelection)) // Search in the dairiesArraylis
            {
                for(int index = 0; index < DeliveryArrayList.deliveryArrayList.size(); index ++)
                {           
                    if(DeliveryArrayList.deliveryArrayList.get(index).toString().toLowerCase().contains(searchKey.toLowerCase()))
                    {
                        displayArea.appendText(DeliveryArrayList.deliveryArrayList.get(index).toString() + "\n");
                    }
                }
                break;
            }// end deliveries if block
            
            if("Supply".equals(userSelection)) // Search in the dairiesArraylis
            {
                for(int index = 0; index < SupplyArrayList.supplyArrayList.size(); index ++)
                {           
                    if(SupplyArrayList.supplyArrayList.get(index).toString().toLowerCase().contains(searchKey.toLowerCase()))
                    {
                        displayArea.appendText(SupplyArrayList.supplyArrayList.get(index).toString() + "\n");
                    }
                }  
                break;
            }// end supplies if block
            this.displayArea.setText(">>>>> We did not find any data that matches your search input <<<<<");
            break; // exits the while loop
        }// end main while loop
        
    }//searchButtonHandler
    
    @FXML
    private void exitButtonHandler(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the application?\nAll data will be ERASED!");
        alert.showAndWait().ifPresent(response -> {
        if (response == ButtonType.OK) {
        Platform.exit();
        }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {        
        farmChoiceMenu.setItems(farmNameOptionList);
        dairyChoiceMenu.setItems(dairyNameOptionList); 
        
        // to display the correct data for the user even if scenes are switched
        this.milkAvailableLabel.setText(String.valueOf(Silo.getAvailableForDelivery()));      
        this.supplyAmountSlider.setMax(Silo.getAvailableForDelivery());
        
        // controls text/edit of the textArea
        this.displayArea.setEditable(false);
        this.displayArea.setStyle("-fx-font-size: 16");  
        this.dateLabel.setText("Local Date: " + "" + String.valueOf(java.time.LocalDate.now()));
    }
        
}
