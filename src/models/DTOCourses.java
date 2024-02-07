/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Sherif
 */
public class DTOCourses {
    private int course_id;
    private String course_name;
    private String description;
    private int creditHours;
    private int department_id;
    private int semester;
    private double avgGPA;

    public DTOCourses(int course_id, String course_name, String description, int creditHours, int department_id, int semester) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.description = description;
        this.creditHours = creditHours;
        this.department_id = department_id;
        this.semester = semester;
    }

    public DTOCourses(String course_name, String description, int creditHours, int department_id, int semester) {
        this.course_name = course_name;
        this.description = description;
        this.creditHours = creditHours;
        this.department_id = department_id;
        this.semester = semester;
    }

    public DTOCourses(int course_id, String course_name, String description, int creditHours, int department_id, int semester, double avgGPA) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.description = description;
        this.creditHours = creditHours;
        this.department_id = department_id;
        this.semester = semester;
        this.avgGPA = avgGPA;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getAvgGPA() {
        return avgGPA;
    }

    public void setAvgGPA(double avgGPA) {
        this.avgGPA = avgGPA;
    }
    
}
