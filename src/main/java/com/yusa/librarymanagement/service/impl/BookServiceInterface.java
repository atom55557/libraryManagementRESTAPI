package com.yusa.librarymanagement.service.impl;

import com.yusa.librarymanagement.enums.BookStatus;
import com.yusa.librarymanagement.enums.Status;
import com.yusa.librarymanagement.model.Book;

import java.util.List;

public interface BookServiceInterface {
    List<Book> getAll();
    List<Book> getBooksByName(String bookName);
    List<Book> getAvailableBooks(BookStatus bookStatus);
    Book createBook(Book book);
    void updateBook(Book book);
    Book updateBookStatus(Long id,BookStatus bookStatus);
    Book getBookById(Long id);
    Book findBookById(long id);
}
