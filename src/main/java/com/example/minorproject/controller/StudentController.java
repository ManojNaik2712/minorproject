package com.example.minorproject.controller;

import com.example.minorproject.Requests.StudentRequest;
import com.example.minorproject.Requests.StudentUpdateRequest;
import com.example.minorproject.model.Student;
import com.example.minorproject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> post(@Valid @RequestBody StudentRequest studentRequest){
        return new ResponseEntity<Student>(studentService.saveStudent(studentRequest),HttpStatus.CREATED);
    }

    @GetMapping
    public Student retrive(@RequestParam int id){
        return studentService.getStudent(id);
    }

    @PutMapping
    public Student update(@RequestBody StudentUpdateRequest studentUpdateRequest,@RequestParam int id){
        return studentService.updateStudent(studentUpdateRequest,id);
    }

    @DeleteMapping
    public String delete(@RequestParam int id){
        return studentService.delete(id);
    }
}
