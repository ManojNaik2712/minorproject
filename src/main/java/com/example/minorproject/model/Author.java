package com.example.minorproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String country;

    @Min(value = 18)
    private int age;

    @Column(unique = true, nullable = false)
    private String email;

    @CreationTimestamp
    private Date createdon;

    @UpdateTimestamp
    private Date updatedon;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"author"})
    private List<Book> bookList;


}
