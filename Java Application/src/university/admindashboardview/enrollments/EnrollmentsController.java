/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.enrollments;

import dataaccesslayer.DAOEnrollments;
import dataaccesslayer.DAOStudents;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.DTODepartments;
import models.DTOEnrollment;
import models.DTOStudents;
import university.admindashboardview.addenrollment.AddenrollmentController;
import university.admindashboardview.home.SidebarController;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class EnrollmentsController implements Initializable {

    @FXML
    private Button delEnroll;
    @FXML
    private TableView<DTOEnrollment> enrollmentsTable;
    @FXML
    private TableColumn<DTOEnrollment, Integer> T_StudentID;
    @FXML
    private TableColumn<DTOEnrollment, String> T_StudentName;
    @FXML
    private TableColumn<DTOEnrollment, Integer> T_CourseID;
    @FXML
    private TableColumn<DTOEnrollment, String> T_Course_name;
    @FXML
    private TableColumn<DTOEnrollment, String> T_Grade;
    @FXML
    private TableColumn<DTOEnrollment, Integer> T_QualityPoints;
    @FXML
    private TableColumn<DTOEnrollment, Double> T_GPA;
    @FXML
    private AnchorPane EnrollAP;

    ObservableList<DTOEnrollment> obsEnroll = FXCollections.observableArrayList();
    @FXML
    private Button newEnroll;
    ArrayList<DTOStudents> newenrollarr = new ArrayList<>();
    ArrayList<DTOEnrollment> updenrollarr = new ArrayList<>();
    @FXML
    private TextField txtFieldGrade;
    @FXML
    private Button GradeUpdateButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        enrollmentsTable.setItems(obsEnroll);
        T_StudentID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getStudentID()).asObject();
        });
        T_CourseID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getCourseID()).asObject();
        });
        T_StudentName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getStudentname());
        });
        T_Course_name.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCoursename());
        });
        T_Grade.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getGrade());
        });
        T_GPA.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getPoints());
        });
        T_QualityPoints.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getQualityPoints()).asObject();
        });
    }

    public void setEnrollList(ArrayList<DTOEnrollment> arr) {
        obsEnroll.setAll(arr);
    }

    @FXML
    private void btnDelEnroll(ActionEvent event) {
        DTOEnrollment selectedEnroll = enrollmentsTable.getSelectionModel().getSelectedItem();
        int result;
        if (selectedEnroll == null) {
            // No enrollment selected, show an alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select an enrollment.");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select an enrollment first!");
            alert.showAndWait();
            // Exit the method without further processing
        } else {
            DAOEnrollments daoEnroll = new DAOEnrollments();
            result = daoEnroll.delEnrollment(selectedEnroll);
            if (result > 0) {
                obsEnroll.remove(selectedEnroll);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null); // No header text
                alert.setContentText("Enrollment has been deleted!");
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

    @FXML
    private void btnNewEnroll(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/university/admindashboardview/home/Home.fxml"));
            Parent root;
            root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            SidebarController sideController = loader.getController();
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/university/admindashboardview/addenrollment/addenrollment.fxml"));
            AnchorPane newAnchorPane = loader2.load();
            sideController.setMainAP(newAnchorPane);
            AddenrollmentController newenroll = loader2.getController();
            DAOStudents studentobj = new DAOStudents();
            newenrollarr = studentobj.getStudents();
            newenroll.setStudentsList(newenrollarr);
            newenrollarr.clear();

        } catch (IOException ex) {
            Logger.getLogger(EnrollmentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnUpdGrade(ActionEvent event) {
        DTOEnrollment selectedEnroll = enrollmentsTable.getSelectionModel().getSelectedItem();
        int result;
        if (selectedEnroll == null) {
            // No enrollment selected, show an alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select an enrollment.");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select an enrollment to update!");
            alert.showAndWait();
            // Exit the method without further processing
        } else if (txtFieldGrade.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Grade issue");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please enter a valid grade A,B,C, or D!");
            alert.showAndWait();
        } else {
            DAOEnrollments daoEnroll = new DAOEnrollments();
            selectedEnroll.setGrade(txtFieldGrade.getText());
            result = daoEnroll.updGrade(selectedEnroll);
            if (result > 0) {
                updenrollarr = daoEnroll.getEnrollments();
                setEnrollList(updenrollarr);
                txtFieldGrade.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null); // No header text
                alert.setContentText("Grade has been updated!");
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

}
