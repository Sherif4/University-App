/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.courses;

import dataaccesslayer.DAOCourses;
import java.net.URL;
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
import models.DTOCourses;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class UpdatepopupController implements Initializable {

    @FXML
    private TextField txtFieldCourseName;
    @FXML
    private TextField txtFieldDescription;
    @FXML
    private TextField txtFieldCreditHours;
    @FXML
    private TextField txtFieldDeptIDCourse;
    @FXML
    private TextField txtFieldSemester;
    @FXML
    private Label labelstate;
    @FXML
    private TextField txtFieldCourseID;
    @FXML
    private Button bttnclear;
    @FXML
    private Button bttnupdCourse1;
    ArrayList<DTOCourses> updArr = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setTxtFieldCourseID(int i) {
        this.txtFieldCourseID.setText(String.valueOf(i));
        txtFieldCourseID.setEditable(false);
    }

    public void setTxtFieldCourseName(String st) {
        this.txtFieldCourseName.setText(st);
    }

    public void setTxtFieldDescription(String st) {
        this.txtFieldDescription.setText(st);
    }

    public void setTxtFieldCreditHours(int i) {
        this.txtFieldCreditHours.setText(String.valueOf(i));
    }

    public void setTxtFieldDeptIDCourse(int i) {
        this.txtFieldDeptIDCourse.setText(String.valueOf(i));
    }

    public void setTxtFieldSemester(int i) {
        this.txtFieldSemester.setText(String.valueOf(i));
    }

    @FXML
    private void updCourseHandler(ActionEvent event) {
        DTOCourses dTOCourses = new DTOCourses(Integer.parseInt(txtFieldCourseID.getText()), txtFieldCourseName.getText(), txtFieldDescription.getText(), Integer.parseInt(txtFieldCreditHours.getText()), Integer.parseInt(txtFieldDeptIDCourse.getText()), Integer.parseInt(txtFieldSemester.getText()));
        DAOCourses daoC = new DAOCourses();
        int result = daoC.updCourse(dTOCourses);
        if (result > 0) {
            updArr = daoC.getCourses();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Course has been added/updated!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Updating failed, Please try again later!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clearCourseHandler(ActionEvent event) {
        txtFieldCourseID.clear();
        txtFieldCourseID.disableProperty();
        txtFieldCourseName.clear();
        txtFieldDescription.clear();
        txtFieldCreditHours.clear();
        txtFieldDeptIDCourse.clear();
        txtFieldSemester.clear();
    }

}
