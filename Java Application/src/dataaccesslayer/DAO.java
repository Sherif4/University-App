/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author Sherif
 */
public class DAO {

    private static DAO instance;
    private static Connection con;
    private static final String JDBC_URL = "jdbc:oracle:oci8:@localhost:1521:XE";
    private static String user = "University";
    private static String password = "123";

    private DAO() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(JDBC_URL, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Faild");

        }

    }


    public static void setUser(String user) {
        DAO.user = user;
    }

    public static void setPassword(String password) {
        DAO.password = password;
    }

    public static DAO getInstance() {//instansation method for signleton object
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        return con;
    }

}
