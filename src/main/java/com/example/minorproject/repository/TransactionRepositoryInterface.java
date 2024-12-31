package com.example.minorproject.repository;

import com.example.minorproject.enums.TransactionType;
import com.example.minorproject.model.Book;
import com.example.minorproject.model.Student;
import com.example.minorproject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositoryInterface extends JpaRepository<Transaction,Integer> {

    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student, TransactionType transactionType);
}
