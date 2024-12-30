package com.example.minorproject.controller;

import com.example.minorproject.Requests.BookUpdaterequest;
import com.example.minorproject.Requests.BookcreateRequest;
import com.example.minorproject.enums.BookFilterType;
import com.example.minorproject.model.Book;
import com.example.minorproject.service.Bookservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping
    public List<Book> getBook(@RequestParam BookFilterType bookFilterType,@RequestParam String filtervalue){
        return bookservice.getBook(bookFilterType,filtervalue);
    }

    @PutMapping
    public Book updateBook(@RequestBody BookUpdaterequest bookUpdaterequest,int id){
        return bookservice.updateBook(bookUpdaterequest,id);
    }

    @DeleteMapping
    public String delete(@RequestParam int id){
        return bookservice.delete(id);
    }

}
