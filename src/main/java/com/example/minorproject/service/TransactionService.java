package com.example.minorproject.service;

import com.example.minorproject.Exceptions.TransacctionException;
import com.example.minorproject.enums.TransactionType;
import com.example.minorproject.model.Book;
import com.example.minorproject.model.Student;
import com.example.minorproject.model.Transaction;
import com.example.minorproject.repository.TransactionRepositoryInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class TransactionService {
    @Autowired
    StudentService studentService;

    @Autowired
    Bookservice bookservice;

    @Autowired
    TransactionRepositoryInterface transactionRepositoryInterface;


    @Value("${book.return.max.allowedDays}")
    int maxDayLimit;


    public String transact(String transactionType,int studentId, int bookId) {

        Optional<Student> student=studentService.findStudent(studentId);

        //check student is present or not
        if(student.isEmpty()){
            throw new TransacctionException("student is not present");
        }

        //check if book is present or not
        Optional<Book> book=bookservice.findBook(bookId);

        if(book.isEmpty()){
            throw new TransacctionException("book is not present");
        }

        //Case of issuing the book
        if(TransactionType.valueOf(transactionType).equals(TransactionType.ISSUE)){

            if(book.get().getStudent() != null){
                throw new TransacctionException("book is already assign");
            }

            Transaction transaction=Transaction.builder()
                    .externalId(UUID.randomUUID().toString())
                    .transactionType(TransactionType.ISSUE)
                    .student(student.get())
                    .book(book.get())
                    .payment(book.get().getCost()).build();
            transactionRepositoryInterface.save(transaction);

            //Assigning student in book
            book.get().setStudent(student.get());
            bookservice.save(book.get());
            return transaction.getExternalId();

        }else if (TransactionType.valueOf(transactionType).equals(TransactionType.RETURN)){

            //Checks if book is  assigned or not
            if(book.get().getStudent() == null){
                throw new TransacctionException("book is not assigned");
            }

            //Checks if book is assigned to same student or not
            if(book.get().getStudent().getId() != studentId){
                throw new TransacctionException("book is not issued to this student");
            }

            Transaction issuedTransaction=transactionRepositoryInterface
                    .findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(book.get(),student.get(),TransactionType.ISSUE);
            double fine=CalculateFine(issuedTransaction);
            Transaction transaction=Transaction.builder()
                    .externalId(UUID.randomUUID().toString())
                    .transactionType(TransactionType.RETURN)
                    .student(student.get())
                    .book(book.get())
                    .payment(book.get().getCost()-fine).build();
            transactionRepositoryInterface.save(transaction);

            //save the changes of student in book
            book.get().setStudent(null);
            bookservice.save(book.get());

            return transaction.getExternalId();

        }
        return null;
    }

    private double CalculateFine(Transaction issuedTransaction) {
        long issuedTime=issuedTransaction.getCreatedon().getTime();
        long returnTime=System.currentTimeMillis();
        long timeDiff=returnTime-issuedTime;

        long dayPassed= TimeUnit.DAYS.convert(timeDiff,TimeUnit.MILLISECONDS);
        if(dayPassed>maxDayLimit){
            return ((dayPassed-maxDayLimit)*1);
        }
        return 0.0;

    }
}
