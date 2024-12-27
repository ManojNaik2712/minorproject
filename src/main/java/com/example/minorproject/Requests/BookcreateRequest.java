package com.example.minorproject.Requests;

import com.example.minorproject.enums.Genre;
import com.example.minorproject.model.Author;
import com.example.minorproject.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookcreateRequest {

    @NotBlank
    private String name;

    @Positive
    private int cost;

    @NotNull
    private Genre genre;

    @NotNull
    private Author author;

    public Book tobook(){
        return Book.builder().name(name).cost(cost).genre(genre).author(this.author).build();
    }

}
