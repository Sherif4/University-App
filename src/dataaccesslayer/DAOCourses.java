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
import models.DTOStudents;

/**
 *
 * @author Sherif
 */
public class DAOCourses {

    public ArrayList<DTOCourses> getCourses() {
        ArrayList<DTOCourses> arrCourses = new ArrayList<DTOCourses>();
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("Select COURSE_ID, COURSE_NAME, DESCRIPTION, CREDIT_HOURS, DEPARTMENT_ID, SEMESTER,calc_avg_gpa(course_id) from courses");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                arrCourses.add(new DTOCourses(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getInt(6), res.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrCourses;
    }

    public ArrayList<DTOCourses> searchCourses(String search) {
        ArrayList<DTOCourses> arrCourses = new ArrayList<DTOCourses>();
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("Select COURSE_ID, COURSE_NAME, DESCRIPTION, CREDIT_HOURS, DEPARTMENT_ID, SEMESTER,calc_avg_gpa(course_id) from courses where lower(COURSE_NAME) like lower(?) or lower(DESCRIPTION) like lower(?)");
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                arrCourses.add(new DTOCourses(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getInt(6), res.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrCourses;
    }

    public int countCourses() {
        int count = 0;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("select Count(Course_id) from courses");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

    public int addCourse(DTOCourses c) {
        int result = 0;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("Insert into courses (Course_name,DESCRIPTION,CREDIT_HOURS,DEPARTMENT_ID,SEMESTER) values(?,?,?,?,?)");
            statement.setString(1, c.getCourse_name());
            statement.setString(2, c.getDescription());
            statement.setInt(3, c.getCreditHours());
            statement.setInt(4, c.getDepartment_id());
            statement.setInt(5, c.getSemester());
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updCourse(DTOCourses c) {
        int result = 0;
        int check = -1;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("select count(*) from courses where course_name =?");
            statement.setString(1, c.getCourse_name());
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                check = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (check > 0) {
            try {
                PreparedStatement statement = DAO.getConnection().prepareCall("update courses set Course_name = ?, DESCRIPTION=? ,CREDIT_HOURS=? ,DEPARTMENT_ID=?,SEMESTER=? where Course_id=?");
                statement.setString(1, c.getCourse_name());
                statement.setString(2, c.getDescription());
                statement.setInt(3, c.getCreditHours());
                statement.setInt(4, c.getDepartment_id());
                statement.setInt(5, c.getSemester());
                statement.setInt(6, c.getCourse_id());
                result = statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
            }
            return result;
        } else if (check == 0) {
            try {
                PreparedStatement statement = DAO.getConnection().prepareCall("Insert into courses (Course_name,DESCRIPTION,CREDIT_HOURS,DEPARTMENT_ID,SEMESTER) values(?,?,?,?,?)");
                statement.setString(1, c.getCourse_name());
                statement.setString(2, c.getDescription());
                statement.setInt(3, c.getCreditHours());
                statement.setInt(4, c.getDepartment_id());
                statement.setInt(5, c.getSemester());
                result = statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
            }
            return result;

        }
        return -1;
    }

    public int delCourse(DTOCourses c) {
        int result = 0;
        int check = -1;
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("delete from enrollment where course_id=?");
            PreparedStatement statement2 = DAO.getConnection().prepareCall("delete from enrollment_history where course_id=?");
            statement.setInt(1, c.getCourse_id());
            statement2.setInt(1, c.getCourse_id());
            check = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (check > 0) {
            try {
                PreparedStatement statement = DAO.getConnection().prepareCall("delete from courses where course_id =?");
                statement.setInt(1, c.getCourse_id());
                result = statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
            }
            return result;
        }
        return result;
    }

    public ArrayList<DTOCourses> checkCourses(DTOStudents st) {
        ArrayList<DTOCourses> chkcourses = new ArrayList<>();
        try {
            PreparedStatement statement = DAO.getConnection().prepareCall("Select * from courses where department_id =? AND semester =? and course_id not in(select course_id from enrollment where student_id=?)");
            statement.setInt(1, st.getDepartment_id());
            statement.setInt(2, st.getSemester());
            statement.setInt(3, st.getStudent_id());
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                chkcourses.add(new DTOCourses(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chkcourses;
    }
}
