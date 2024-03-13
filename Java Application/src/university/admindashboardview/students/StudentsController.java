/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.students;

import dataaccesslayer.DAOCourses;
import dataaccesslayer.DAOStudents;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    @FXML
    private Button updStudent;

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
        DTOStudents selectedStudent = studentsTables.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Selection");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select Student first!");
            alert.showAndWait();
        } else {

            Alert Confirm = new Alert(Alert.AlertType.CONFIRMATION);
            Confirm.setTitle("Warning");
            Confirm.setHeaderText(null);
            Confirm.setContentText("Deleting Student will delete all enrollments related to him/her, are you sure you want to proceed?");
            Confirm.showAndWait().
                    ifPresent(response -> {
                        if (response == ButtonType.OK) {

                            DAOStudents daoS = new DAOStudents();
                            int result = daoS.delStudent(selectedStudent);
                            if (result > 0) {
                                obsStudents.remove(selectedStudent);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText(null); // No header text
                                alert.setContentText("Student has been deleted!");
                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Failed");
                                alert.setHeaderText(null); // No header text
                                alert.setContentText("Deletion failed, Please try again later!");
                                alert.showAndWait();
                            }
                        }
                    }
                    );
        }
    }

    @FXML
    private void btnUpdStudent(ActionEvent event) {
        DTOStudents selectedStudent = studentsTables.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Selection");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select Student first!");
            alert.showAndWait();
        } else {
            try {
                System.out.println(selectedStudent);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentUpdatepop.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                stage.show();
                StudentUpdatepopController upopup = fxmlLoader.getController();
                //txtFieldCourseID.setEditable(false);
                upopup.setTxtFieldName(selectedStudent.getFull_name());
                upopup.setTxtFieldEmail(selectedStudent.getEmail());
                upopup.setTxtFieldPhoneNumber(selectedStudent.getPhoneNumber());
                upopup.setTxtFieldCity(selectedStudent.getCity());
                upopup.setTxtFieldStreet(selectedStudent.getStreet());
                upopup.setTxtFieldDeptID(selectedStudent.getDepartment_id());
                upopup.setTxtFieldStuSemester(selectedStudent.getSemester());
                upopup.setTxtFieldID(selectedStudent.getStudent_id());

            } catch (IOException ex) {
                Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
