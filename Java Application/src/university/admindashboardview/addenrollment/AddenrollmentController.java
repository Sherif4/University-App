/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.addenrollment;

import dataaccesslayer.DAOCourses;
import dataaccesslayer.DAOEnrollments;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.DTOCourses;
import models.DTOEnrollment;
import models.DTOStudents;
import university.admindashboardview.enrollments.EnrollmentsController;
import university.admindashboardview.home.SidebarController;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class AddenrollmentController implements Initializable {

    @FXML
    private AnchorPane EnrollAP;
    @FXML
    private Button AddEnroll;
    @FXML
    private TableView<DTOStudents> StudentenrollTable;
    @FXML
    private TableColumn<DTOStudents, Integer> T_StudentID;
    @FXML
    private TableColumn<DTOStudents, String> T_StudentName;
    @FXML
    private TableColumn<DTOStudents, Integer> T_StudSemester;
    @FXML
    private TableColumn<DTOStudents, Integer> T_StudDept;
    @FXML
    private TableView<DTOCourses> CoursesenrollTable;
    @FXML
    private TableColumn<DTOCourses, Integer> T_CourseID1;
    @FXML
    private TableColumn<DTOCourses, String> T_Course_name1;
    @FXML
    private TableColumn<DTOCourses, Integer> T_CourseSemester;

    ObservableList<DTOCourses> obsenrollCourses = FXCollections.observableArrayList();
    ObservableList<DTOStudents> obsenrollStudents = FXCollections.observableArrayList();
    ArrayList<DTOEnrollment> arrEnroll;

    @FXML
    private Button Coursecheck;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StudentenrollTable.setItems(obsenrollStudents);
        T_StudentID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getStudent_id()).asObject();
        });
        T_StudentName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getFull_name());
        });
        T_StudSemester.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getSemester()).asObject();
        });
        T_StudDept.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getDepartment_id()).asObject();
        });
    }

    public void setStudentsList(ArrayList<DTOStudents> st) {
        obsenrollStudents.setAll(st);
    }

    public void setCoursesList(ArrayList<DTOCourses> arr) {
        obsenrollCourses.setAll(arr);
    }

    @FXML
    private void btnAddEnroll(ActionEvent event) {
        DTOStudents selectedST = StudentenrollTable.getSelectionModel().getSelectedItem();
        DTOCourses selectedCOR = CoursesenrollTable.getSelectionModel().getSelectedItem();
        int result;
        if (selectedST == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selection error");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select a Student!");
            alert.showAndWait();
        } else if (selectedCOR == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selection error");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select the course!");
            alert.showAndWait();
        } else {
            DAOEnrollments newenroll = new DAOEnrollments();
            result = newenroll.addEnrollment(selectedST, selectedCOR);
            if (result > 0) {
                obsenrollCourses.remove(selectedCOR);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null); // No header text
                alert.setContentText("Enrollment has been added!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failed");
                alert.setHeaderText(null); // No header text
                alert.setContentText("Failed, Please try again later!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void btnCheckcourses(ActionEvent event) {
        DTOStudents selectedStudent = StudentenrollTable.getSelectionModel().getSelectedItem();
        int result;
        if (selectedStudent == null) {
            // No enrollment selected, show an alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selecting a student");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select a student!");
            alert.showAndWait();
            // Exit the method without further processing
        } else {
            ArrayList<DTOCourses> checkedcourses = new ArrayList<>();
            DAOCourses daocourses = new DAOCourses();
            checkedcourses = daocourses.checkCourses(selectedStudent);
            setCoursesList(checkedcourses);
            CoursesenrollTable.setItems(obsenrollCourses);
            T_CourseID1.setCellValueFactory(cellData -> {
                return new SimpleIntegerProperty(cellData.getValue().getCourse_id()).asObject();
            });

            T_Course_name1.setCellValueFactory(cellData -> {
                return new SimpleStringProperty(cellData.getValue().getCourse_name());
            });

            T_CourseSemester.setCellValueFactory(cellData -> {
                return new SimpleIntegerProperty(cellData.getValue().getSemester()).asObject();
            });

        }
    }

    @FXML
    private void backtoEnroll(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/university/admindashboardview/home/Home.fxml"));
            Parent root;
            root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            SidebarController sideController = loader.getController();
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/university/admindashboardview/enrollments/enrollments.fxml"));
            AnchorPane newAnchorPane = loader2.load();
            sideController.setMainAP(newAnchorPane);
            EnrollmentsController enrollontroller = loader2.getController();
            DAOEnrollments enrollObj = new DAOEnrollments();
            arrEnroll = enrollObj.getEnrollments();
            enrollontroller.setEnrollList(arrEnroll);
            arrEnroll.clear();
        } catch (IOException ex) {
            Logger.getLogger(AddenrollmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
