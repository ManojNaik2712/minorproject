package com.example.minorproject.service;

import com.example.minorproject.Requests.StudentRequest;
import com.example.minorproject.Requests.StudentUpdateRequest;
import com.example.minorproject.model.Student;
import com.example.minorproject.repository.StudentRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService{
    @Autowired
    StudentRepositoryInterface studentRepositoryInterface;

    public Student saveStudent(StudentRequest studentRequest) {
        Student student=studentRequest.toStudent();
        return studentRepositoryInterface.save(student);

    }

    public Student getStudent(int id) {
        return studentRepositoryInterface.findById(id).get();
    }

    public Student updateStudent(StudentUpdateRequest studentUpdateRequest, int id) {
        Student student=studentRepositoryInterface.findById(id).get();
        student.setEmail(studentUpdateRequest.getEmail());
        student.setAddress(studentUpdateRequest.getAddress());
        student.setName(studentUpdateRequest.getName());
        student.setPhoneNo(studentUpdateRequest.getPhoneNo());
        studentRepositoryInterface.save(student);
        return student;

    }

    public String delete(int id) {
        studentRepositoryInterface.deleteById(id);
        return "student details are deleted succesfully";
    }
}
