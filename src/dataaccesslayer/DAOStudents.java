/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DTOStudents;

/**
 *
 * @author Sherif
 */
public class DAOStudents {
    public ArrayList<DTOStudents> getStudents(){
        ArrayList<DTOStudents> arrStudents = new ArrayList<DTOStudents>();
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("select STUDENT_ID,FIRST_NAME||' '||LAST_NAME, EMAIL, PHONE_NUMBER, DOB, STREET, CITY, GPA, DEPARTMENT_ID, SEMESTER from STUDENTS");
            ResultSet res = statement.executeQuery();
            while (res.next()){
                arrStudents.add(new DTOStudents(res.getInt(1), res.getString(2), res.getString(3), 
                        res.getString(4), res.getDate(5).toLocalDate(), res.getString(6), res.getString(7), 
                        res.getDouble(8), res.getInt(9), res.getInt(10)));
               
            } 
                    
                    } catch (SQLException ex) {
            Logger.getLogger(DAOStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrStudents;
    }
    
    public int delStudent(DTOStudents st){
        int result = 0;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("Delete from Students where Student_id = ?");
            statement.setInt(1, st.getStudent_id());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int countStudents(){
        int count=0;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("select Count(Student_id) from students");
            ResultSet res = statement.executeQuery();
            while (res.next()){
                count = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    
    public int addStudent(DTOStudents st){
        int result =0;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("Insert into Students (FIRST_NAME,LAST_NAME, EMAIL, PHONE_NUMBER, DOB, STREET, City, DEPARTMENT_ID)values (?,?,?,?,?,?,?,?)");
            statement.setString(1, st.getFirst_name());
            statement.setString(2, st.getLast_name());
            statement.setString(3, st.getEmail());
            statement.setString(4, st.getPhoneNumber());
            statement.setDate(5, st.getDOB());
            statement.setString(6, st.getStreet());
            statement.setString(7, st.getCity());
            statement.setInt(8, st.getDepartment_id());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return result;
    }
}
