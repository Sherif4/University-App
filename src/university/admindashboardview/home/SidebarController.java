/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.admindashboardview.home;

import dataaccesslayer.DAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.DTOStudents;
import models.DTOCourses;
import dataaccesslayer.DAOCourses;
import dataaccesslayer.DAODepartments;
import dataaccesslayer.DAOEnrollments;
import dataaccesslayer.DAOStudents;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.DTODepartments;
import models.DTOEnrollment;
import university.admindashboardview.courses.CoursesController;
import university.admindashboardview.departments.DepartmentsController;
import university.admindashboardview.enrollments.EnrollmentsController;
import university.admindashboardview.students.StudentsController;

/**
 *
 * @author Sherif
 */
public class SidebarController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button btndashboard;
    @FXML
    private TextField txtfieldSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private AnchorPane mainAP;

    public ArrayList<DTOStudents> arrStudents;
    public ArrayList<DTOCourses> arrCourses;
    public ArrayList<DTODepartments> arrDept;
    public ArrayList<DTOEnrollment> arrEnroll;
    public ArrayList<DTOStudents> arrSearchItems;
    public ArrayList<DTOCourses> arrSearchUser;
    public ArrayList<DTODepartments> arrSearchDept;
    public ArrayList<DTOEnrollment> arrSearchEnroll;
    String view;
    @FXML
    private Button btnStudents;
    @FXML
    private Button btnDepartments;
    @FXML
    private Button btnCourses;
    @FXML
    private Button btnEnrollment;
    @FXML
    private Button btnsignout;

    public void setMainAP(AnchorPane mainAP) {
        this.mainAP.getChildren().setAll(mainAP);
    }

    @FXML
    void clickCourse(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/university/admindashboardview/courses/courses.fxml"));
            AnchorPane anchorpane = loader.load();
            mainAP.getChildren().setAll(anchorpane);
            view = "Courses";
            CoursesController coursesController = loader.getController();
            DAOCourses coursesObj = new DAOCourses();
            arrCourses = coursesObj.getCourses();
            coursesController.setCoursesList(arrCourses);
            arrCourses.clear();

        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void clickDashboard(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/university/admindashboardview/Dashboard.fxml"));
            AnchorPane anchorpane = loader.load();
            mainAP.getChildren().setAll(anchorpane);
            view = "Dashboard";
        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clickDashboard(null);

    }

    @FXML
    private void clickStudents(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/university/admindashboardview/students/students.fxml"));
            AnchorPane anchorpane = loader.load();
            mainAP.getChildren().setAll(anchorpane);
            view = "Students";
            StudentsController studentsController = loader.getController();
            DAOStudents studentsObj = new DAOStudents();
            arrStudents = studentsObj.getStudents();
            studentsController.setStudentsList(arrStudents);
            arrStudents.clear();
        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void clickDepts(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/university/admindashboardview/departments/departments.fxml"));
            AnchorPane anchorpane = loader.load();
            mainAP.getChildren().setAll(anchorpane);
            view = "Departments";
            DepartmentsController deptsontroller = loader.getController();
            DAODepartments deptsObj = new DAODepartments();
            arrDept = deptsObj.getDepartments();
            deptsontroller.setDeptsList(arrDept);
            arrDept.clear();
        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickEnroll(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/university/admindashboardview/enrollments/enrollments.fxml"));
            AnchorPane anchorpane = loader.load();
            mainAP.getChildren().setAll(anchorpane);
            view = "Enrollments";
            EnrollmentsController enrollontroller = loader.getController();
            DAOEnrollments enrollObj = new DAOEnrollments();
            arrEnroll = enrollObj.getEnrollments();
            enrollontroller.setEnrollList(arrEnroll);
            arrEnroll.clear();
        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void search(ActionEvent event) {
        if (txtfieldSearch.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Search box is empty!");
            alert.showAndWait();
        } else {
            String search = txtfieldSearch.getText();
            switch (view) {
                case "Students":
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/university/admindashboardview/students/students.fxml"));
                    AnchorPane anchorpane;
                    try {
                        anchorpane = loader.load();
                        mainAP.getChildren().setAll(anchorpane);
                    } catch (IOException ex) {
                        Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    StudentsController studentsController = loader.getController();
                    DAOStudents studentsObj = new DAOStudents();
                    arrStudents = studentsObj.searchStudents(search);
                    studentsController.setStudentsList(arrStudents);
                    arrStudents.clear();
                    break;
                case "Courses":
                    try {
                        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/university/admindashboardview/courses/courses.fxml"));
                        AnchorPane anchorpane2 = loader2.load();
                        mainAP.getChildren().setAll(anchorpane2);
                        CoursesController coursesController = loader2.getController();
                        DAOCourses coursesObj = new DAOCourses();
                        arrCourses = coursesObj.searchCourses(search);
                        coursesController.setCoursesList(arrCourses);
                        arrCourses.clear();

                    } catch (IOException ex) {
                        Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case "Departments":
                    try {
                        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/university/admindashboardview/departments/departments.fxml"));
                        AnchorPane anchorpane3 = loader3.load();
                        mainAP.getChildren().setAll(anchorpane3);
                        DepartmentsController deptsontroller = loader3.getController();
                        DAODepartments deptsObj = new DAODepartments();
                        arrDept = deptsObj.searchDepartments(search);
                        deptsontroller.setDeptsList(arrDept);
                        arrDept.clear();
                    } catch (IOException ex) {
                        Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case "Enrollments":

                    FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/university/admindashboardview/enrollments/enrollments.fxml"));
                    AnchorPane anchorpane4;
                    try {
                        anchorpane4 = loader4.load();
                        mainAP.getChildren().setAll(anchorpane4);
                    } catch (IOException ex) {
                        Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    EnrollmentsController enrollontroller = loader4.getController();
                    DAOEnrollments enrollObj = new DAOEnrollments();
                    arrEnroll = enrollObj.searchEnrollments(search);
                    enrollontroller.setEnrollList(arrEnroll);
                    arrEnroll.clear();
                    break;
                case "Dashboard":
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Failed");
                    alert.setHeaderText(null); // No header text
                    alert.setContentText("No search available in this scene");
                    alert.showAndWait();
                default:
                    System.out.println("failed search");
                    break;
            }
        }
    }

    @FXML
    private void clickSignOut(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/university/Login/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
