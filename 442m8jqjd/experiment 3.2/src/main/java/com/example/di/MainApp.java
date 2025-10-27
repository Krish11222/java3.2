package com.example.di;

import com.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("--- Part A: Dependency Injection Demonstration ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Student student = context.getBean("myStudent", Student.class);
        student.displayStudentInfo();

        ((AnnotationConfigApplicationContext) context).close();
        System.out.println("--------------------------------------------------");
    }
}