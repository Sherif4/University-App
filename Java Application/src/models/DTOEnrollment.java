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
public class DTOEnrollment {
    private int studentID;
    private int courseID;
    private String grade;
    private double points;
    private int qualityPoints;
    private String studentname;
    private String coursename;

    public DTOEnrollment(int studentID, int courseID, String grade, double points, int qualityPoints) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
        this.points = points;
        this.qualityPoints = qualityPoints;
    }

    public DTOEnrollment(int studentID, int courseID, String grade, double points, int qualityPoints, String studentname, String coursename) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
        this.points = points;
        this.qualityPoints = qualityPoints;
        this.studentname = studentname;
        this.coursename = coursename;
    }

    public DTOEnrollment(int studentID, int courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getQualityPoints() {
        return qualityPoints;
    }

    public void setQualityPoints(int qualityPoints) {
        this.qualityPoints = qualityPoints;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
    
    
}
