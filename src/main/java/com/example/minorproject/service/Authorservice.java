package com.example.minorproject.service;

import com.example.minorproject.Requests.AuthorUpdateRequest;
import com.example.minorproject.model.Author;
import com.example.minorproject.repository.AuthorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Authorservice {
    @Autowired
    AuthorInterface authorInterface;
    public Author saveAuthor(Author author) {
        return authorInterface.save(author);
    }

    public Author getAuthor(int id) {
        return authorInterface.findById(id).get();
    }

    public Author updateAuthor(AuthorUpdateRequest authorUpdateRequest, int id) {
        Author author=authorInterface.findById(id).get();
        author.setAge(authorUpdateRequest.getAge());
        author.setEmail(authorUpdateRequest.getEmail());
        author.setName(authorUpdateRequest.getName());
        authorInterface.save(author);
        return author;

    }

    public String delete(int id) {
        authorInterface.deleteById(id);
        return "author is deleted succesfully";
    }
}
