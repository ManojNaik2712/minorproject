package com.example.minorproject.repository;

import com.example.minorproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryInterface extends JpaRepository<Book,Integer> {
}
