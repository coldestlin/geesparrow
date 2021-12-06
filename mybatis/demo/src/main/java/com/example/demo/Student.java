package com.example.demo;

import java.util.Objects;

public class Student {
   
    Integer id;
    Integer studentID;
    String name;


    public Student() {
    }

    public Student(Integer id, Integer studentID, String name) {
        this.id = id;
        this.studentID = studentID;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentID() {
        return this.studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student id(Integer id) {
        setId(id);
        return this;
    }

    public Student studentID(Integer studentID) {
        setStudentID(studentID);
        return this;
    }

    public Student name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(studentID, student.studentID) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentID, name);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", studentID='" + getStudentID() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }

}
