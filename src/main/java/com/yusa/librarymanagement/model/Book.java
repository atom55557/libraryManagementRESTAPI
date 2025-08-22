package com.yusa.librarymanagement.model;

import com.yusa.librarymanagement.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bookName;
    private String bookAuthor;
    private int releaseDate;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;



}
