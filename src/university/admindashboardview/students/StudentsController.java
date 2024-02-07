/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.students;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.DTOStudents;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class StudentsController implements Initializable {

    @FXML
    private Button delStudent;
    @FXML
    private TableView<DTOStudents> studentsTables;
    @FXML
    private TableColumn<DTOStudents, Integer> T_id;
    @FXML
    private TableColumn<DTOStudents, String> T_fullname;
    @FXML
    private TableColumn<DTOStudents, String> T_email;
    @FXML
    private TableColumn<DTOStudents, String> T_phone;
    @FXML
    private TableColumn<DTOStudents, Date> T_DOB;
    @FXML
    private TableColumn<DTOStudents, String> T_street;
    @FXML
    private TableColumn<DTOStudents, String> T_city;
    @FXML
    private TableColumn<DTOStudents, Integer> T_deptid;
    @FXML
    private TableColumn<DTOStudents, Integer> T_semster;
    @FXML
    private TableColumn<DTOStudents, Double> T_CGPA;
    ObservableList<DTOStudents> obsStudents = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        studentsTables.setItems(obsStudents);

        T_id.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getStudent_id()).asObject();
        });
        T_fullname.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getFull_name());
        });
        T_email.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getEmail());
        });
        T_phone.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getPhoneNumber());
        });
        T_street.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getStreet());
        });
        T_city.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCity());
        });
        T_DOB.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getDOB());
        });
        T_deptid.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getDepartment_id()).asObject();
        });
        T_semster.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getSemester()).asObject();
        });
        T_CGPA.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getGpa());
        });
    }

    public void setStudentsList(ArrayList<DTOStudents> st) {
        obsStudents.setAll(st);
    }

    @FXML
    private void btnDelStudent(ActionEvent event) {
    }

}
