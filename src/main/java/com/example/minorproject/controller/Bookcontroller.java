package com.example.minorproject.controller;

import com.example.minorproject.Requests.BookcreateRequest;
import com.example.minorproject.model.Book;
import com.example.minorproject.service.Bookservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/book")
public class Bookcontroller {
    @Autowired
    Bookservice bookservice;

    @PostMapping
    public ResponseEntity<Book> savebook(@Valid @RequestBody BookcreateRequest bookCreateRequest){
       Book book= bookservice.savebook(bookCreateRequest);
       return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

}
