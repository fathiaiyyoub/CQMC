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

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author fali8
 */
public class Utility {
    
    // example
    // Utility.changeScene (getClass(), event, "myFXML.FXML");
    public static void changeScene(Class aClass, Event aEvent, String sceneFileString) throws Exception
    {
        Parent root = FXMLLoader.load(aClass.getResource(sceneFileString)); //gets resource
        Scene scene = new Scene(root); // new Scene
        Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow(); // new stage
        stage.setScene(scene); // links the scene to the stage 
        stage.show(); // shows the stage
    }
}
