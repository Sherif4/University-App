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
import javafx.scene.control.TextField;
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

        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clickDashboard(null);

    }

    /*
    void clickUsers(ActionEvent event) {
        if (DashboardController.getServer() == null) {
            showAlert("Server is not running. Please start the server.");
        } else if (!DashboardController.getServer().isServerRunning()) {
            showAlert("Server is not running. Please start the server.");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/users/Users.fxml"));
                AnchorPane anchorpane = loader.load();
                mainAP.getChildren().setAll(anchorpane);
                view = "Users";
                UsersController userController = loader.getController();
                DAOUser userObj = new DAOUser();
                try {
                    arrUser = userObj.getUsers();
                    userController.setItemList(arrUser);
                    arrUser.clear();
                } catch (SQLException ex) {
                    Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void search(ActionEvent event) throws IOException, SQLException {
        if (DashboardController.getServer() == null) {
            showAlert("Server is not running. Please start the server.");
        } else if (!DashboardController.getServer().isServerRunning()) {
            showAlert("Server is not running. Please start the server.");
        } else {
            switch (view) {
                case "Users":
                    if (txtfieldSearch.getText().isEmpty() != true) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/users/Users.fxml"));
                        AnchorPane anchorpane = loader.load();
                        mainAP.getChildren().setAll(anchorpane);
                        UsersController userController = loader.getController();
                        DAOUser usersearchObj = new DAOUser();
                        arrSearchUser = usersearchObj.searchUsers(txtfieldSearch.getText());
                        userController.setItemList(arrSearchUser);
                        arrSearchUser.clear();
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/users/Users.fxml"));
                        AnchorPane anchorpane = loader.load();
                        mainAP.getChildren().setAll(anchorpane);
                        UsersController userController = loader.getController();
                        DAOUser userObj = new DAOUser();
                        arrUser = userObj.getUsers();
                        userController.setItemList(arrUser);
                        arrUser.clear();
                    }
                    break;

                case "Items":
                    if (txtfieldSearch.getText().isEmpty() != true) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/items/Items.fxml"));
                        AnchorPane anchorpane = loader.load();
                        mainAP.getChildren().setAll(anchorpane);
                        ItemsController itemsController = loader.getController();
                        DAOItem itemsearchObj = new DAOItem();
                        arrSearchItems = itemsearchObj.searchItems(txtfieldSearch.getText());
                        itemsController.setItemList(arrSearchItems);
                        arrSearchItems.clear();
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/items/Items.fxml"));
                        AnchorPane anchorpane = loader.load();
                        mainAP.getChildren().setAll(anchorpane);
                        view = "Items";
                        ItemsController itemsController = loader.getController();
                        DAOItem itemObj = new DAOItem();
                        arrItems = itemObj.getItems();
                        itemsController.setItemList(arrItems);
                        arrItems.clear();
                    }
                    break;
                default:
                    System.out.println("failed search");
                    break;
            }
        }
    }

   

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Server Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

     */
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
    }

}
