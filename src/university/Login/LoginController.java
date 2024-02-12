/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university.Login;

import dataaccesslayer.DAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField txtFieldPassword;
    @FXML
    private Button btnlogin;
    @FXML
    private Label labelWarning;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnLoginAction(ActionEvent event) {
        if (txtFieldUsername.getText().length() == 0 || txtFieldPassword.getText().length() == 0) {
            labelWarning.setText("Enter your login data");
        } else if (txtFieldUsername.getText().equalsIgnoreCase("University") && txtFieldPassword.getText().equalsIgnoreCase("123")) {
            Platform.runLater(new Runnable() {
                public void run() {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/university/admindashboardview/home/Home.fxml"));
                    Parent root;
                    try {
                        DAO dao = DAO.getInstance();
                        root = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(true);
                        stage.show();

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } else {
            labelWarning.setText("Username or password is incorrect!");

        }
    }

}
