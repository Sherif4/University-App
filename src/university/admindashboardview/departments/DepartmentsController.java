/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.departments;

import dataaccesslayer.DAODepartments;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import models.DTODepartments;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class DepartmentsController implements Initializable {

    @FXML
    private AnchorPane DepartmentsAP;
    @FXML
    private Button deldept;
    @FXML
    private TableView<DTODepartments> DeptsTable;
    @FXML
    private TableColumn<DTODepartments, Integer> T_id;
    @FXML
    private TableColumn<DTODepartments, String> T_name;
    @FXML
    private TableColumn<DTODepartments, Double> T_avgGPA;

    ObservableList<DTODepartments> obsDepts = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DeptsTable.setItems(obsDepts);
        T_id.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getDepartment_id()).asObject();
        });
        T_name.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDept_name());
        });

        T_avgGPA.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getAvg_GPA());
        });

    }

    public void setDeptsList(ArrayList<DTODepartments> arr) {
        obsDepts.setAll(arr);
    }

    @FXML
    private void btnDeleteDept(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Failed");
        alert.setHeaderText(null); // No header text
        alert.setContentText("Please contact the database adminstrator in order to delete a department!");
        alert.showAndWait();

    }

}
