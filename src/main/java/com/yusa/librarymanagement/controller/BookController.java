package com.yusa.librarymanagement.controller;

import com.yusa.librarymanagement.enums.BookStatus;
import com.yusa.librarymanagement.model.Book;
import com.yusa.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @DeleteMapping
    public void deleteBook(@RequestParam long bookId){
        bookService.deleteBookById(bookId);
    }

    @PutMapping
    public Book updateBookStatus(@RequestParam long bookId,@RequestParam BookStatus bookStatus){
        return bookService.updateBookStatus(bookId,bookStatus);
    }


}
