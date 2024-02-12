CREATE or replace Function calc_avg_gpa(V_Course_id NUMBER)
RETURN NUMBER
IS
    V_Avg_GPA NUMBER(8,4);
BEGIN
    select avg(points) into V_Avg_GPA from ENROLLMENT
    WHERE COURSE_ID = V_Course_id
    GROUP by COURSE_ID;

    RETURN V_Avg_GPA;
end;

SELECT course_id, course_name, calc_avg_gpa(course_id)
from COURSES;