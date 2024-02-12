CREATE or replace Function calc_avg_DEPT_gpa(V_Dept_id NUMBER)
RETURN NUMBER
IS
    V_Avg_GPA NUMBER(8,4);
BEGIN
    select avg(points) into V_Avg_GPA 
    from ENROLLMENT e
    inner JOIN COURSES c ON
        c.COURSE_ID = e.COURSE_ID
    WHERE 
        c.DEPARTMENT_ID = V_Dept_id;

    RETURN V_Avg_GPA;
end;
