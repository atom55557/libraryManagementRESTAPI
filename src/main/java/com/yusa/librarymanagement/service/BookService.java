package com.yusa.librarymanagement.service;

import com.yusa.librarymanagement.enums.BookStatus;
import com.yusa.librarymanagement.model.Book;
import com.yusa.librarymanagement.repository.BookRepository;
import com.yusa.librarymanagement.service.impl.BookServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceInterface {
    private final BookRepository bookRepository;


    public Book findBookById(long bookId){
        return bookRepository.findById(bookId).orElseThrow();
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks(BookStatus bookStatus){
        return bookRepository.findAll().stream()
                .filter(book -> bookStatus.equals(book.getBookStatus()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByName(String bookName){
        return bookRepository.findByBookName(bookName);

    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }


    public void deleteBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
    }

    public Book updateBookStatus(Long id, BookStatus bookStatus){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setBookStatus(bookStatus);
        return bookRepository.save(book);
    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }
}
