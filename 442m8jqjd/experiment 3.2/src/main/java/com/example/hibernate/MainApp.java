package com.example.hibernate;

public class MainApp {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();

        System.out.println("--- Part B: Hibernate CRUD Demonstration ---");

        // 1. Create (Save)
        Student student1 = new Student("John", "Doe", "john.doe@example.com");
        studentDao.saveStudent(student1);
        System.out.println("1. Created Student ID: " + student1.getId());
        int newId = student1.getId();

        // 2. Read (Get)
        Student readStudent = studentDao.getStudent(newId);
        System.out.println("2. Read Student: " + readStudent);

        // 3. Update
        readStudent.setLastName("Smith");
        studentDao.updateStudent(readStudent);
        Student updatedStudent = studentDao.getStudent(newId);
        System.out.println("3. Updated Last Name to: " + updatedStudent.getLastName());

        // 4. Delete
        studentDao.deleteStudent(newId);
        Student deletedCheck = studentDao.getStudent(newId);
        System.out.println("4. Delete Check (Should be null): " + deletedCheck);

        System.out.println("------------------------------------------");
    }
}