CREATE OR REPLACE TRIGGER CGPA_TRIGGER
FOR INSERT OR UPDATE OR DELETE ON ENROLLMENT
COMPOUND TRIGGER

  -- Declare a collection to store affected student IDs
  TYPE student_id_list IS TABLE OF NUMBER;
  enrolling_students student_id_list := student_id_list();

  -- Before Statement section
  BEFORE STATEMENT IS
  BEGIN
    -- Initialize the collection before each statement
    enrolling_students := student_id_list();
  END BEFORE STATEMENT;

  -- After Each Row section
  AFTER EACH ROW IS
  BEGIN
    -- Store the affected student ID in the collection
    IF INSERTING OR UPDATING THEN
      enrolling_students.EXTEND;
      enrolling_students(enrolling_students.LAST) := :OLD.STUDENT_ID;
    ELSIF DELETING THEN
      enrolling_students.EXTEND;
      enrolling_students(enrolling_students.LAST) := :OLD.STUDENT_ID;
    END IF;
  END AFTER EACH ROW;

  -- After Statement section
  AFTER STATEMENT IS
  BEGIN
    -- Call the procedure for each affected student ID
    FOR i IN 1..enrolling_students.COUNT LOOP
      CALC_CGPA(enrolling_students(i));
    END LOOP;
  END AFTER STATEMENT;

END CGPA_TRIGGER;
/
