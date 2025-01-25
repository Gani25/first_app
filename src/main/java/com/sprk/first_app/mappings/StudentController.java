package com.sprk.first_app.mappings;

import com.sprk.first_app.exceptions.StudentIndexException;
import com.sprk.first_app.models.Address;
import com.sprk.first_app.models.ErrorResponse;
import com.sprk.first_app.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestMapping("/sprk")
@RestController
public class StudentController {


    @GetMapping("/student")
    public Student getSingleStudent() {
        Student student = new Student();

        student.setRollNo(10);
        student.setFirstName("John");
        student.setLastName("Smith");
        student.setGender("Male");

        return student;

    }

    @GetMapping("/students-list")
    public List<Student> getListStudent() {
        List<Student> students = Arrays.asList(
                new Student(10, "John", "Smith", "Male",
                        new Address("Navi Mumbai", "Maharshtra", "India")
                ),
                new Student(20, "Susan", "Doe", "Female",
                        new Address("Pune", "Maharshtra", "India")
                ),
                new Student(30, "Pratik", "Mhatre", "Male",
                        new Address("Gurgaon", "Delhi", "India")

                ),
                new Student(40, "Rakesh", "Sharma", "Male",
                        new Address("Kochi", "Kerla", "India")
                )
        );
        return students;

    }
    @GetMapping("/student/{index}")
    public Student getStudentByIndex(@PathVariable int index) {
        List<Student> students = Arrays.asList(
                new Student(10, "John", "Smith", "Male",
                        new Address("Navi Mumbai", "Maharshtra", "India")
                ),
                new Student(20, "Susan", "Doe", "Female",
                        new Address("Pune", "Maharshtra", "India")
                ),
                new Student(30, "Pratik", "Mhatre", "Male",
                        new Address("Gurgaon", "Delhi", "India")

                ),
                new Student(40, "Rakesh", "Sharma", "Male",
                        new Address("Kochi", "Kerla", "India")
                )
        );
        if(index<0 || index>=students.size()) {
            String message = "Index: "+index+" is incorrect";
            throw new StudentIndexException(HttpStatus.NOT_FOUND.value(), message);
        }
        return students.get(index);
    }

    @ExceptionHandler(StudentIndexException.class)
    public ErrorResponse handleStudentIndexException(StudentIndexException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ex.getStatus());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return  errorResponse;
    }
}
