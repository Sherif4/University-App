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
public class DTODepartments {
    private int department_id;
    private String dept_name;
    private double avg_GPA;

    public DTODepartments(int department_id, String dept_name) {
        this.department_id = department_id;
        this.dept_name = dept_name;
    }

    public DTODepartments(int department_id, String dept_name, double avg_GPA) {
        this.department_id = department_id;
        this.dept_name = dept_name;
        this.avg_GPA = avg_GPA;
    }

    public DTODepartments(String dept_name) {
        this.dept_name = dept_name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public double getAvg_GPA() {
        return avg_GPA;
    }

    public void setAvg_GPA(double avg_GPA) {
        this.avg_GPA = avg_GPA;
    }
    
    
}
