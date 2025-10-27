package com.example.di;

public class Course {
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseInfo() {
        return "Enrolled in course: " + courseName;
    }
}