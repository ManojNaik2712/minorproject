package com.example.minorproject.Requests;

import com.example.minorproject.enums.Genre;
import lombok.Data;

@Data
public class BookUpdaterequest {
    private String name;

    private int cost;

    private Genre genre;
}
