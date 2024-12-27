package com.example.minorproject.model;

import com.example.minorproject.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String externalId;

    @Enumerated
    private TransactionType transactionType;

    private double payment;

    @CreationTimestamp
    private Date createdon;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Book book;

}
