/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Sherif
 */
public class DTOStudents {

    private int student_id;
    private String first_name;
    private String last_name;
    private String full_name;
    private String email;
    private String phoneNumber;
    private Date DOB;
    private String street;
    private String city;
    private double gpa;
    private int department_id;
    private int semester;

    public DTOStudents(int student_id, String full_name, String email, String phoneNumber, LocalDate DOB, String street, String city, double gpa, int department_id, int semester) {
        this.student_id = student_id;
        this.full_name = full_name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.DOB = java.sql.Date.valueOf(DOB);
        this.street = street;
        this.city = city;
        this.gpa = gpa;
        this.department_id = department_id;
        this.semester = semester;
    }

    public DTOStudents(int student_id, String first_name, String last_name, String email, String phoneNumber, LocalDate DOB, String street, String city, double gpa, int department_id, int semester) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.DOB = java.sql.Date.valueOf(DOB);
        this.street = street;
        this.city = city;
        this.gpa = gpa;
        this.department_id = department_id;
        this.semester = semester;
    }

    public DTOStudents(String first_name, String last_name, String email, String phoneNumber, LocalDate DOB, String street, String city, int department_id, int semester) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.DOB = java.sql.Date.valueOf(DOB);
        this.street = street;
        this.city = city;
        this.department_id = department_id;
        this.semester = semester;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = java.sql.Date.valueOf(DOB);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
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

}
