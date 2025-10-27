package com.example.di;

public class Student {
    private String studentName;
    private Course enrolledCourse;

    // Dependency Injection via Constructor
    public Student(String studentName, Course enrolledCourse) {
        this.studentName = studentName;
        this.enrolledCourse = enrolledCourse;
    }

    public void displayStudentInfo() {
        System.out.println("Student Name: " + studentName);
        System.out.println("Course Details: " + enrolledCourse.getCourseInfo());
    }
}