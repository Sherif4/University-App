/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.courses;

import dataaccesslayer.DAOCourses;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.DTOCourses;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class CoursesController implements Initializable {

    @FXML
    private TableColumn<DTOCourses, Integer> T_id;
    @FXML
    private TableColumn<DTOCourses, String> T_name;
    @FXML
    private TableColumn<DTOCourses, String> T_desc;
    @FXML
    private TableColumn<DTOCourses, Integer> T_semster;
    @FXML
    private TableColumn<DTOCourses, Integer> T_deptid;
    @FXML
    private TableColumn<DTOCourses, Double> T_avgGPA;

    @FXML
    private TableView<DTOCourses> CoursesTable;
    @FXML
    private AnchorPane CoursesAP;
    @FXML
    private Button delCourse;
    ObservableList<DTOCourses> obsCourses = FXCollections.observableArrayList();
    @FXML
    private Button updCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CoursesTable.setItems(obsCourses);

        T_id.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getCourse_id()).asObject();
        });

        T_name.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourse_name());
        });

        T_desc.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDescription());
        });

        T_deptid.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getDepartment_id()).asObject();
        });

        T_semster.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getSemester()).asObject();
        });

        T_avgGPA.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getAvgGPA());
        });

    }

    public void setCoursesList(ArrayList<DTOCourses> arr) {
        obsCourses.setAll(arr);
    }

    @FXML
    private void btnDeleteCourse(ActionEvent event) {
        DTOCourses selectedCourse = CoursesTable.getSelectionModel().getSelectedItem();
        if (selectedCourse == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Course Selection");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select course first!");
            alert.showAndWait();
        } else {
            Alert Confirm = new Alert(Alert.AlertType.CONFIRMATION);
            Confirm.setTitle("Warning");
            Confirm.setHeaderText(null);
            Confirm.setContentText("Deleting course will delete all enrollments related to it, are you sure you want to proceed?");
            Confirm.showAndWait().
                    ifPresent(response -> {
                        if (response == ButtonType.OK) {

                            DAOCourses daoc = new DAOCourses();
                            int result = daoc.delCourse(selectedCourse);
                            if (result > 0) {
                                obsCourses.remove(selectedCourse);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText(null); // No header text
                                alert.setContentText("Course has been deleted!");
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
    private void btnUpdateCourse(ActionEvent event
    ) {
        DTOCourses selectedCourse = CoursesTable.getSelectionModel().getSelectedItem();
        if (selectedCourse == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Course Selection");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select course first!");
            alert.showAndWait();
        } else {
            try {
                System.out.println(selectedCourse);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Updatepopup.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                stage.show();
                UpdatepopupController upopup = fxmlLoader.getController();
                //txtFieldCourseID.setEditable(false);
                upopup.setTxtFieldCourseID(selectedCourse.getCourse_id());
                upopup.setTxtFieldCourseName(selectedCourse.getCourse_name());
                upopup.setTxtFieldDescription(selectedCourse.getDescription());
                upopup.setTxtFieldCreditHours(selectedCourse.getCreditHours());
                upopup.setTxtFieldSemester(selectedCourse.getSemester());
                upopup.setTxtFieldDeptIDCourse(selectedCourse.getDepartment_id());

            } catch (IOException ex) {
                Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
