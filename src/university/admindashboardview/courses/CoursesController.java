/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.courses;

import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
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
    }

}
