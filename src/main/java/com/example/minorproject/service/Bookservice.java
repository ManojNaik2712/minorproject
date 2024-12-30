package com.example.minorproject.service;

import com.example.minorproject.Requests.BookUpdaterequest;
import com.example.minorproject.Requests.BookcreateRequest;
import com.example.minorproject.enums.BookFilterType;
import com.example.minorproject.model.Author;
import com.example.minorproject.model.Book;
import com.example.minorproject.repository.AuthorInterface;
import com.example.minorproject.repository.BookRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Bookservice {
    @Autowired
    AuthorInterface authorInterface;

    @Autowired
    BookRepositoryInterface bookRepositoryInterface;


    public Book savebook(BookcreateRequest bookCreateRequest) {
        Book book=bookCreateRequest.tobook();
        Author author=book.getAuthor();

        Author authorfromdb= authorInterface.findByEmail(author.getEmail());

        if(authorfromdb== null ){
            authorfromdb=authorInterface.save(author);
        }
        book.setAuthor(authorfromdb);

        return bookRepositoryInterface.save(book);

    }

    public List<Book> getBook(BookFilterType bookFilterType, String filtervalue) {
        switch (bookFilterType){
            case Name -> {
                return bookRepositoryInterface.findByname(filtervalue);
            }
            case Author -> {
                return bookRepositoryInterface.findByauthorName(filtervalue);
            }
            case Id -> {
                return bookRepositoryInterface.findAllById(new ArrayList<>(Integer.parseInt(filtervalue)));
            }
        }
        return new ArrayList<>();
    }

    public Book updateBook(BookUpdaterequest bookUpdaterequest, int id) {
        Book book=bookRepositoryInterface.findById(id).get();
        book.setName(bookUpdaterequest.getName());
        book.setCost(bookUpdaterequest.getCost());
        book.setGenre(bookUpdaterequest.getGenre());
        bookRepositoryInterface.save(book);
        return book;
    }

    public String delete(int id) {
        bookRepositoryInterface.deleteById(id);
        return "book is deleted succesfully";

    }
}
