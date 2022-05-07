SET IMPLICIT_TRANSACTIONS OFF;

USE master;

DROP DATABASE IF EXISTS TestDB;
CREATE DATABASE TestDB;
USE TestDB;

DROP TABLE IF EXISTS StudentGrade;
DROP TABLE IF EXISTS StudentGroup;
DROP TABLE IF EXISTS Lesson;
DROP TABLE IF EXISTS Subject;
DROP TABLE IF EXISTS "Group";
DROP TABLE IF EXISTS Room;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS Teacher;
DROP TABLE IF EXISTS Grade;
DROP TABLE IF EXISTS Person;

CREATE TABLE Grade (
    IdGrade int  NOT NULL,
    Value tinyint  NOT NULL,
    ValueInWords varchar(20)  NOT NULL,
    CONSTRAINT check_grade_value CHECK (Value BETWEEN 2 AND 5),
    CONSTRAINT Grade_pk PRIMARY KEY  (IdGrade)
);

CREATE TABLE "Group" (
    IdGroup int  NOT NULL,
    Number tinyint  NOT NULL,
    Type char(1)  NOT NULL,
    CONSTRAINT Group_pk PRIMARY KEY  (IdGroup)
);

CREATE TABLE Room (
    IdRoom int  NOT NULL,
    Number smallint  NOT NULL,
    Capacity smallint  NOT NULL,
    CONSTRAINT check_room_capacity CHECK (Capacity >= 0),
    CONSTRAINT Room_pk PRIMARY KEY  (IdRoom)
);

CREATE TABLE Person (
    IdPerson int  NOT NULL,
    Name varchar(30)  NOT NULL,
    Surname varchar(30)  NOT NULL,
    DateOfBirth date  NOT NULL,
    CONSTRAINT check_person_date_of_birth CHECK (DateOfBirth < GETDATE()),
    CONSTRAINT Person_pk PRIMARY KEY  (IdPerson)
);

CREATE TABLE Teacher (
    IdTeacher int  NOT NULL,
    AcademicRank varchar(30) NULL,
    CONSTRAINT Teacher_pk PRIMARY KEY  (IdTeacher),
    CONSTRAINT Teacher_Person FOREIGN KEY (IdTeacher) REFERENCES Person (IdPerson)
);

CREATE TABLE Student (
    IdStudent int  NOT NULL,
    IndexNumber varchar(10)  NOT NULL,
    DateOfEnrollment date  NOT NULL,
    CONSTRAINT check_student_date_of_enrollment CHECK (DateOfEnrollment < GETDATE()),
    CONSTRAINT Student_pk PRIMARY KEY  (IdStudent),
    CONSTRAINT Student_Person FOREIGN KEY (IdStudent) REFERENCES Person (IdPerson)
);

CREATE TABLE Subject (
    IdSubject int  NOT NULL,
    Name varchar(30)  NOT NULL,
    Acronym varchar(4)  NOT NULL,
    CONSTRAINT Subject_pk PRIMARY KEY  (IdSubject)
);

CREATE TABLE Lesson (
    IdLesson int  NOT NULL,
    IdTeacher int  NOT NULL,
    IdSubject int  NOT NULL,
    IdRoom int  NOT NULL,
    IdGroup int  NOT NULL,
    StartTime datetime  NOT NULL,
    EndTime datetime  NOT NULL,
    CONSTRAINT check_time CHECK (StartTime < EndTime),
    CONSTRAINT Lesson_pk PRIMARY KEY  (IdLesson),
    CONSTRAINT Lesson_Teacher FOREIGN KEY (IdTeacher) REFERENCES Teacher (IdTeacher),
    CONSTRAINT Lesson_Subject FOREIGN KEY (IdSubject) REFERENCES Subject (IdSubject),
    CONSTRAINT Lesson_Room FOREIGN KEY (IdRoom) REFERENCES Room (IdRoom),
    CONSTRAINT Lesson_Group FOREIGN KEY (IdGroup) REFERENCES "Group" (IdGroup)
);

CREATE TABLE StudentGrade (
    IdStudentGrade int  NOT NULL,
    IdTeacher int  NOT NULL,
    IdStudent int  NOT NULL,
    IdGrade int  NOT NULL,
    IdSubject int  NOT NULL,
    CONSTRAINT StudentGrade_pk PRIMARY KEY  (IdStudentGrade),
    CONSTRAINT StudentGrade_Teacher FOREIGN KEY (IdTeacher) REFERENCES Teacher (IdTeacher),
    CONSTRAINT StudentGrade_Student FOREIGN KEY (IdStudent) REFERENCES Student (IdStudent),
    CONSTRAINT StudentGrade_Grade FOREIGN KEY (IdGrade) REFERENCES Grade (IdGrade),
    CONSTRAINT StudentGrade_Subject FOREIGN KEY (IdSubject) REFERENCES Subject (IdSubject)
);

CREATE TABLE StudentGroup (
    IdGroup int  NOT NULL,
    IdStudent int  NOT NULL,
    CONSTRAINT StudentGroup_pk PRIMARY KEY  (IdStudent,IdGroup),
    CONSTRAINT StudentGroup_Group FOREIGN KEY (IdGroup) REFERENCES "Group" (IdGroup),
    CONSTRAINT StudentGroup_Student FOREIGN KEY (IdStudent) REFERENCES Student (IdStudent)
);

CREATE INDEX index_person_name on Person (Name ASC);
CREATE INDEX index_room_capacity on Room (Capacity ASC);
CREATE INDEX index_student_index_number on Student (IndexNumber ASC);
CREATE INDEX index_student_date_of_enrollment on Student (DateOfEnrollment ASC);