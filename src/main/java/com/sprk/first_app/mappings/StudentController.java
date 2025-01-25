package com.sprk.first_app.mappings;

import com.sprk.first_app.exceptions.StudentException;
import com.sprk.first_app.exceptions.StudentIndexException;
import com.sprk.first_app.exceptions.StudentIndexStringException;
import com.sprk.first_app.models.Address;
import com.sprk.first_app.models.ErrorResponse;
import com.sprk.first_app.models.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RequestMapping("/sprk")
@RestController
public class StudentController {

    private List<Student> students;

    @PostConstruct
    public void init() {
        students = Arrays.asList(
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
    }


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

        return students;

    }
    @GetMapping("/student/{idx}")
    public Student getStudentByIndex(@PathVariable String idx) {
        if(!Pattern.matches("^\\d{1,}$",idx)){
            String message = "Index: "+idx+" can be number only";
            throw new StudentIndexStringException(HttpStatus.BAD_REQUEST.value(),message );
        }
        int index = Integer.parseInt(idx);
        if(index<0 || index>=students.size()) {
            String message = "Index: "+index+" is incorrect";
            throw new StudentIndexException(HttpStatus.NOT_FOUND.value(), message);
        }
        return students.get(index);
    }

    /*@ExceptionHandler(StudentException.class)
    public ResponseEntity<?> handleStudentIndexException(StudentException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ex.getStatus());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus()));
    }*/

    /*
    @ExceptionHandler(StudentIndexException.class)
    public ResponseEntity<?> handleStudentIndexException(StudentIndexException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ex.getStatus());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(StudentIndexStringException.class)
    public ResponseEntity<?> handleStudentIndexStringException(StudentIndexStringException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ex.getStatus());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }
    */
}
