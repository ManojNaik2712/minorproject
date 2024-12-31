package com.example.minorproject.controller;

import com.example.minorproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity transact(@RequestParam int studentId,
                                   @RequestParam int bookId,
                                   @RequestParam String transactionType){
        return new ResponseEntity<>(transactionService.transact(transactionType,studentId,bookId), HttpStatus.CREATED);
    }

}
