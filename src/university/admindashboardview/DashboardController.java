/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview;

import dataaccesslayer.DAOCourses;
import dataaccesslayer.DAOStudents;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import models.DTOCourses;
import models.DTOStudents;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dashboardAP;
    @FXML
    private Label labelstate;
    @FXML
    private Label labelStudents;
    @FXML
    private Label labelCourses;
    @FXML
    private TextField txtFieldFirstName;
    @FXML
    private TextField txtFieldLastName;
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
    private DatePicker dateField;
    @FXML
    private Button bttnAddStudent;
    @FXML
    private Label labelstate1;
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
    private Button bttnAddCourse;
    @FXML
    private TextField txtFieldStuSemester;

    String countCourses;
    String countStudents;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAllStudents();
        getAllCourses();
    }

    private void getAllStudents() {
        DAOStudents sCount = new DAOStudents();
        countStudents = Integer.toString(sCount.countStudents());
        labelStudents.setText(countStudents);
    }

    private void getAllCourses() {
        DAOCourses cCount = new DAOCourses();
        countCourses = Integer.toString(cCount.countCourses());
        labelCourses.setText(countCourses);
    }

    @FXML
    private void addStudentHandler(ActionEvent event) {

        int result;
        String fname = txtFieldFirstName.getText();
        String lname = txtFieldLastName.getText();
        String email = txtFieldEmail.getText();
        String phone = txtFieldPhoneNumber.getText();
        int dept_id = Integer.parseInt(txtFieldDeptID.getText());
        String street = txtFieldStreet.getText();
        String city = txtFieldCity.getText();
        LocalDate dob = dateField.getValue();
        int semester = Integer.parseInt(txtFieldStuSemester.getText());
        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || phone.isEmpty() || dept_id == 0 || street.isEmpty() || city.isEmpty()) {
            labelstate1.setText("Failed! please check your entries.");
            labelstate1.setTextFill(Color.RED);
        } else {
            DAOStudents student = new DAOStudents();
            result = student.addStudent(new DTOStudents(fname, lname, email, phone, dob, street, city, dept_id, semester));
            if (result > 0) {
                labelstate1.setText("Student has been added successfully!");
                labelstate1.setTextFill(Color.web("#00ff00"));
                getAllStudents();
                getAllCourses();
                txtFieldFirstName.clear();
                txtFieldLastName.clear();
                txtFieldEmail.clear();
                txtFieldPhoneNumber.clear();
                txtFieldDeptID.clear();
                txtFieldStreet.clear();
                txtFieldCity.clear();
                txtFieldStuSemester.clear();
                dateField.setValue(null);
            } else {
                labelstate1.setText("Failed! please check your entries.");
                labelstate1.setTextFill(Color.RED);
            }

        }

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Connection Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void addCourseHandler(ActionEvent event) {

        int result;
        String courseName = txtFieldCourseName.getText();
        String courseDesc = txtFieldDescription.getText();
        int creditHours = Integer.parseInt(txtFieldCreditHours.getText());
        int Deptid = Integer.parseInt(txtFieldDeptIDCourse.getText());
        int semster = Integer.parseInt(txtFieldSemester.getText());
        if (courseName.isEmpty() || courseDesc.isEmpty() || creditHours == 0 || Deptid == 0 || semster == 0) {
            labelstate.setText("Failed! please check your entries.");
            labelstate.setTextFill(Color.RED);
        } else {
            DAOCourses daoCourse = new DAOCourses();
            result = daoCourse.addCourse(new DTOCourses(courseName, courseDesc, creditHours, Deptid, semster));
            if (result > 0) {
                labelstate.setText("Course has been added successfully!");
                labelstate.setTextFill(Color.web("#00ff00"));
                getAllStudents();
                getAllCourses();
                txtFieldCourseName.clear();
                txtFieldDescription.clear();
                txtFieldCreditHours.clear();
                txtFieldDeptIDCourse.clear();
                txtFieldSemester.clear();
            } else {
                labelstate.setText("Failed! please check your entries.");
                labelstate.setTextFill(Color.RED);
            }

        }
    }

}
