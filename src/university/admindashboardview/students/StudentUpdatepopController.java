/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.students;

import dataaccesslayer.DAOStudents;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.DTOStudents;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class StudentUpdatepopController implements Initializable {

    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPhoneNumber;
    @FXML
    private TextField txtFieldDeptID;
    @FXML
    private TextField txtFieldStreet;
    @FXML
    private TextField txtFieldCity;
    @FXML
    private TextField txtFieldStuSemester;
    @FXML
    private Button bttnUpdStudent;
    @FXML
    private Label labelstate1;
    @FXML
    private TextField txtFieldID;
    @FXML
    private TextField txtFieldName;
    public ArrayList<DTOStudents> arrStudents;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTxtFieldID(int i) {
        this.txtFieldID.setText(String.valueOf(i));
        txtFieldID.setEditable(false);
    }

    public void setTxtFieldName(String st) {
        this.txtFieldName.setText(st);
        txtFieldName.setEditable(false);
    }

    public void setTxtFieldEmail(String st) {
        this.txtFieldEmail.setText(st);
    }

    public void setTxtFieldPhoneNumber(String st) {
        this.txtFieldPhoneNumber.setText(st);
    }

    public void setTxtFieldDeptID(int i) {
        this.txtFieldDeptID.setText(String.valueOf(i));
        txtFieldDeptID.setEditable(false);
    }

    public void setTxtFieldStreet(String st) {
        this.txtFieldStreet.setText(st);
    }

    public void setTxtFieldCity(String st) {
        this.txtFieldCity.setText(st);
    }

    public void setTxtFieldStuSemester(int i) {
        this.txtFieldStuSemester.setText(String.valueOf(i));
    }

    @FXML
    private void updStudentHandler(ActionEvent event) {
        DTOStudents dTOStudent = new DTOStudents(Integer.parseInt(txtFieldID.getText()), txtFieldEmail.getText(), txtFieldPhoneNumber.getText(), txtFieldStreet.getText(), txtFieldCity.getText(), Integer.parseInt(txtFieldStuSemester.getText()));
        DAOStudents daoS = new DAOStudents();
        int result = daoS.updStudent(dTOStudent);
        if (result > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Student has been updated!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Updating failed, Please try again later!");
            alert.showAndWait();
        }

    }

}
