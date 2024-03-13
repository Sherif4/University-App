/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Sherif
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button btnRemoveDept;
    @FXML
    private Button btnRemoveStudent;
    @FXML
    private Button btnRemoveCourse;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void removeDept(ActionEvent event) {
    }

    @FXML
    private void removeStudent(ActionEvent event) {
    }

    @FXML
    private void removeCourse(ActionEvent event) {
    }
    
}
