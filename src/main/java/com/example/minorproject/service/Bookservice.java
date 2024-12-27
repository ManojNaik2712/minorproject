package com.example.minorproject.service;

import com.example.minorproject.Requests.BookcreateRequest;
import com.example.minorproject.model.Author;
import com.example.minorproject.model.Book;
import com.example.minorproject.repository.AuthorInterface;
import com.example.minorproject.repository.BookRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if(authorfromdb==null){
            authorfromdb=authorInterface.save(author);
        }
        book.setAuthor(authorfromdb);
        return bookRepositoryInterface.save(book);

    }
}
