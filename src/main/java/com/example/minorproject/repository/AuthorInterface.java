package com.example.minorproject.repository;

import com.example.minorproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorInterface extends JpaRepository<Author,Integer> {

    Author findByEmail(String email);
}
