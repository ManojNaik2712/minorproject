package com.example.minorproject.dto;

import com.example.minorproject.enums.Genre;
import com.example.minorproject.model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class bookDTO {
    private String name;
    private int cost;
    private Genre genre;
    private Author author;

}
