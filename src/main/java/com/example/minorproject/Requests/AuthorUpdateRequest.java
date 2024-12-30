package com.example.minorproject.Requests;

import com.example.minorproject.model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthorUpdateRequest {
    private String name;

    private int age;

    private String email;

    public Author to(){
        return Author.builder().name(name).age(age).email(email).build();
    }
}
