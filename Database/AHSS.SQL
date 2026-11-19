-- Create User Table 
CREATE TABLE users (
    user_id        NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email          VARCHAR2(100) NOT NULL UNIQUE,
    pass  VARCHAR2(255) NOT NULL,
    user_role      VARCHAR2(10) NOT NULL
                   CHECK (user_role IN ('ADMIN','TEACHER','STUDENT'))
);
-- Create admin Table
CREATE TABLE admin (
    admin_id   NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    full_name  VARCHAR2(100) NOT NULL,
    user_id    NUMBER NOT NULL UNIQUE,
    CONSTRAINT fk_admin_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
-- Create Teacher Table
CREATE TABLE teacher (
    teacher_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    full_name  VARCHAR2(100) NOT NULL,
    phone      NUMBER(20),
    status     VARCHAR2(10) DEFAULT 'ACTIVE' NOT NULL
               CHECK (status IN ('ACTIVE','LEFT')),
    user_id    NUMBER NOT NULL UNIQUE,
    CONSTRAINT fk_teacher_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
--Create table Classes
CREATE TABLE classes (
    class_id          NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    class_name        VARCHAR2(50) NOT NULL UNIQUE,
    teacher_id  NUMBER,
    CONSTRAINT fk_class_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);
--Create table Student
CREATE TABLE student (
    student_id      NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    full_name       VARCHAR2(100) NOT NULL,
    gender          VARCHAR2(1) CHECK (gender IN ('M','F')),
    date_of_birth   DATE,
    admission_date  DATE DEFAULT SYSDATE,
    class_id        NUMBER NOT NULL,
    user_id         NUMBER NOT NULL UNIQUE,
    CONSTRAINT fk_student_class FOREIGN KEY (class_id) REFERENCES classes(class_id),
    CONSTRAINT fk_student_user  FOREIGN KEY (user_id)  REFERENCES users(user_id)
);
-- create table subject
CREATE TABLE subject (
    subject_id    NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    subject_name  VARCHAR2(80) NOT NULL UNIQUE
);
--Create Table Teacher_Assignment
CREATE TABLE teacher_assignment (
    assignment_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    teacher_id    NUMBER NOT NULL,
    class_id      NUMBER NOT NULL,
    subject_id    NUMBER NOT NULL,
    CONSTRAINT fk_ta_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id),
    CONSTRAINT fk_ta_class   FOREIGN KEY (class_id)   REFERENCES classes(class_id),
    CONSTRAINT fk_ta_subject FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    CONSTRAINT uq_ta UNIQUE (class_id, subject_id)
);
-- Create table Student attendance 
CREATE TABLE student_attendance (
    attendance_id          NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id             NUMBER NOT NULL,
    class_id               NUMBER NOT NULL,
    attendance_date        DATE   NOT NULL,
    status                 VARCHAR2(1) NOT NULL CHECK (status IN ('P','A','L')),
    marked_by_teacher_id   NUMBER NOT NULL,
    CONSTRAINT fk_sa_student FOREIGN KEY (student_id) REFERENCES student(student_id),
    CONSTRAINT fk_sa_class   FOREIGN KEY (class_id)   REFERENCES classes(class_id),
    CONSTRAINT fk_sa_teacher FOREIGN KEY (marked_by_teacher_id) REFERENCES teacher(teacher_id),
    CONSTRAINT uq_sa UNIQUE (student_id, attendance_date)
);
-- Create table Teacher attendance
CREATE TABLE teacher_attendance (
    attendance_id        NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    teacher_id           NUMBER NOT NULL,
    attendance_date      DATE   NOT NULL,
    status               VARCHAR2(1) NOT NULL CHECK (status IN ('P','A','L')),
    admin_id   NUMBER NOT NULL,
    CONSTRAINT fk_ta2_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id),
    CONSTRAINT fk_ta2_admin   FOREIGN KEY (admin_id) REFERENCES admin(admin_id),
    CONSTRAINT uq_ta2 UNIQUE (teacher_id, attendance_date)
);
-- Create table TimeTable
CREATE TABLE timetable (
    timetable_id  NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    class_id      NUMBER NOT NULL,
    subject_id    NUMBER NOT NULL,
    teacher_id    NUMBER NOT NULL,
    day_of_week   VARCHAR2(10) NOT NULL
                  CHECK (day_of_week IN ('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY')),
    start_time    VARCHAR2(5) NOT NULL,   -- e.g. 08:00
    end_time      VARCHAR2(5) NOT NULL,   -- e.g. 08:40
    CONSTRAINT fk_tt_class   FOREIGN KEY (class_id)   REFERENCES classes(class_id),
    CONSTRAINT fk_tt_subject FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    CONSTRAINT fk_tt_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);
-- create  table exam
CREATE TABLE exam (
    exam_id              NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    exam_name            VARCHAR2(80) NOT NULL,
    class_id             NUMBER NOT NULL,
    subject_id           NUMBER NOT NULL,
    exam_date            DATE   NOT NULL,
    total_marks          NUMBER(5) NOT NULL CHECK (total_marks > 0),
    created_by_teacher_id NUMBER NOT NULL,
    CONSTRAINT fk_exam_class   FOREIGN KEY (class_id)   REFERENCES classes(class_id),
    CONSTRAINT fk_exam_subject FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    CONSTRAINT fk_exam_teacher FOREIGN KEY (created_by_teacher_id) REFERENCES teacher(teacher_id)
);
-- create table result
CREATE TABLE result (
    result_id       NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id      NUMBER NOT NULL,
    exam_id         NUMBER NOT NULL,
    obtained_marks  NUMBER(5,2) NOT NULL CHECK (obtained_marks >= 0),
    grade           VARCHAR2(2),
    CONSTRAINT fk_res_student FOREIGN KEY (student_id) REFERENCES student(student_id),
    CONSTRAINT fk_res_exam    FOREIGN KEY (exam_id)    REFERENCES exam(exam_id),
    CONSTRAINT uq_result UNIQUE (student_id, exam_id)
);
-- create table fee 
CREATE TABLE fee (
    fee_id      NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id  NUMBER NOT NULL,
    fee_month   VARCHAR2(20) NOT NULL,   -- e.g. 'November 2026'
    amount      NUMBER(10,2) NOT NULL CHECK (amount > 0),
    due_date    DATE NOT NULL,
    pay_date    DATE,
    status      VARCHAR2(10) DEFAULT 'UNPAID' NOT NULL
                CHECK (status IN ('PAID','UNPAID')),
    CONSTRAINT fk_fee_student FOREIGN KEY (student_id) REFERENCES student(student_id)
);

-- create table report
CREATE OR REPLACE VIEW student_report_vw AS
SELECT s.student_id, s.full_name AS student_name, c.class_name,
       sub.subject_name, e.exam_name, e.exam_date,
       e.total_marks, r.obtained_marks, r.grade
FROM   result r
JOIN   student s   ON s.student_id = r.student_id
JOIN   exam e      ON e.exam_id    = r.exam_id
JOIN   subject sub ON sub.subject_id = e.subject_id
JOIN   classes c   ON c.class_id   = s.class_id;


-- DEMO DATA INSERTION 
INSERT INTO users (email, pass, user_role) VALUES ('admin@ahss.edu',   'admin123',   'ADMIN');
INSERT INTO users (email, pass, user_role) VALUES ('teacher1@ahss.edu','teacher123', 'TEACHER');
INSERT INTO users (email, pass, user_role) VALUES ('student1@ahss.edu','student123', 'STUDENT');

INSERT INTO admin   (full_name, user_id) VALUES ('Admin One', 1);
INSERT INTO teacher (full_name, phone, status, user_id)
VALUES ('Teacher One', 03000000000, 'ACTIVE', 2);
INSERT INTO classes (class_name, teacher_id) VALUES ('Class 1', 9);
INSERT INTO student (full_name, gender, date_of_birth, admission_date, class_id, user_id)
VALUES ('Student One', 'M', DATE '2019-03-15', DATE '2026-04-01', 3, 3);
INSERT INTO subject (subject_name) VALUES ('Mathematics');
INSERT INTO teacher_assignment (teacher_id, class_id, subject_id) VALUES (9, 3, 1);

SELECT * FROM teacher_assignment



