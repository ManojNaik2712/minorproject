package com.example.minorproject.controller;

import com.example.minorproject.Requests.AuthorUpdateRequest;
import com.example.minorproject.model.Author;
import com.example.minorproject.service.Authorservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class Authorcontroller {
    @Autowired
    Authorservice authorservice;

    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody Author author){
        return new ResponseEntity<Author>(authorservice.saveAuthor(author), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Author get(@PathVariable("id") int id){
        return authorservice.getAuthor(id);
    }

    @PutMapping
    public Author update(@RequestBody AuthorUpdateRequest authorUpdateRequest,@RequestParam int id){
        return authorservice.updateAuthor(authorUpdateRequest,id);
    }

    @DeleteMapping
    public String delete(@RequestParam int id){
       return authorservice.delete(id);
    }
}
