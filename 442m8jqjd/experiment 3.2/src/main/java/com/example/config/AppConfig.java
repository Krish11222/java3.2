package com.example.config;

import com.example.di.Course;
import com.example.di.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Course javaCourse() {
        return new Course("Advanced Java and Spring Framework");
    }

    @Bean
    public Student myStudent() {
        return new Student("Alice Smith", javaCourse());
    }
}