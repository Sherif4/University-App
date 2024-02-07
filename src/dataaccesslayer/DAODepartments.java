/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTODepartments;

/**
 *
 * @author Sherif
 */
public class DAODepartments {
    public ArrayList<DTODepartments> getDepartments(){
        ArrayList<DTODepartments> arrDept = new ArrayList<DTODepartments>();
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("SELECT DEPARTMENT_ID, department_name, calc_avg_DEPT_gpa(DEPARTMENT_ID) from DEPARTMENTS order by department_id");
            ResultSet res = statement.executeQuery();
            while (res.next()){
                arrDept.add(new DTODepartments(res.getInt(1), res.getString(2), res.getDouble(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODepartments.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrDept;
    }
    
    public int delDepartment(DTODepartments dept){
        int result = 0;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("Delete from Department where department_id = ?");
            statement.setInt(1, dept.getDepartment_id());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODepartments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
