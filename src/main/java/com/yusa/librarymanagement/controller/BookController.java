package com.yusa.librarymanagement.controller;

import com.yusa.librarymanagement.enums.BookStatus;
import com.yusa.librarymanagement.model.Book;
import com.yusa.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<Book> getBooks(
            @RequestParam(required = false) BookStatus bookStatus,
            @RequestParam(required = false) String bookName
    ){
        if( bookName != null ){
            return bookService.getBooksByName(bookName);
        }
        if(bookStatus != null){
            return bookService.getAvailableBooks(bookStatus);
        }else{
            return bookService.getAll();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public void deleteBook(@RequestParam long bookId){
        bookService.deleteBookById(bookId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Book updateBookStatus(@RequestParam long bookId,@RequestParam BookStatus bookStatus){
        return bookService.updateBookStatus(bookId,bookStatus);
    }


}
