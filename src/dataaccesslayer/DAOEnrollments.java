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
import models.DTOCourses;
import models.DTOEnrollment;
import models.DTOStudents;

/**
 *
 * @author Sherif
 */
public class DAOEnrollments {

    public ArrayList<DTOEnrollment> getEnrollments() {
        ArrayList<DTOEnrollment> arrEnroll = new ArrayList<DTOEnrollment>();
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("SELECT e.STUDENT_ID, e.Course_id, e.grade, e.points, e.quality_points, s.FIRST_NAME || ' ' || s.LAST_NAME, COURSE_NAME\n"
                    + "FROM ENROLLMENT e\n"
                    + "INNER JOIN STUDENTS s ON e.STUDENT_ID = s.STUDENT_ID\n"
                    + "INNER JOIN COURSES C ON e.COURSE_ID = C.COURSE_ID");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                arrEnroll.add(new DTOEnrollment(res.getInt(1), res.getInt(2), res.getString(3), res.getDouble(4), res.getInt(5), res.getString(6), res.getString(7)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnrollments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrEnroll;

    }

    public int delEnrollment(DTOEnrollment enroll) {
        int result = 0;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("Delete from enrollment where student_id=? and course_id=?");
            statement.setInt(1, enroll.getStudentID());
            statement.setInt(2, enroll.getCourseID());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnrollments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int addEnrollment(DTOStudents st, DTOCourses cor) {
        int result = 0;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("INSERT into ENROLLMENT(STUDENT_ID, COURSE_ID) VALUES(?, ?)");
            statement.setInt(1, st.getStudent_id());
            statement.setInt(2, cor.getCourse_id());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnrollments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updGrade(DTOEnrollment enroll){
        int result=0;
        try {
            PreparedStatement statement= DAO.getConnection().prepareCall("Update ENROLLMENT set grade = upper(?) where student_id=? and course_id=?");
            statement.setString(1, enroll.getGrade());
            statement.setInt(2, enroll.getStudentID());
            statement.setInt(3, enroll.getCourseID());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnrollments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
