package com.example.minorproject.repository;

import com.example.minorproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositoryInterface extends JpaRepository<Student,Integer> {

}
