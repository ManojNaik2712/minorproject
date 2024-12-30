package com.example.minorproject.repository;

import com.example.minorproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepositoryInterface extends JpaRepository<Book,Integer> {
     List<Book> findByname(String filtervalue);

    List<Book> findByauthorName(String filtervalue);
}
