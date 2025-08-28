package com.yusa.librarymanagement.service;

import com.yusa.librarymanagement.enums.BookStatus;
import com.yusa.librarymanagement.model.Book;
import com.yusa.librarymanagement.repository.BookRepository;
import com.yusa.librarymanagement.service.impl.BookServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceInterface {
    private final BookRepository bookRepository;


    public Book findBookById(long bookId){
        return bookRepository.findById(bookId).orElseThrow();
    }

    @Cacheable(value = "books")
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @Cacheable(value = "books")
    public List<Book> getAvailableBooks(BookStatus bookStatus){
        return bookRepository.findAll().stream()
                .filter(book -> bookStatus.equals(book.getBookStatus()))
                .toList();
    }

    @Cacheable(value = "books")
    public List<Book> getBooksByName(String bookName){
        return bookRepository.findByBookName(bookName);

    }

    @CacheEvict(value = "books", allEntries = true)
    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    @CacheEvict(value = "books", allEntries = true)
    public void deleteBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
    }

    @CacheEvict(value = "books", allEntries = true)
    public Book updateBookStatus(Long id, BookStatus bookStatus){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setBookStatus(bookStatus);
        return bookRepository.save(book);
    }

    @CacheEvict(value = "books",  allEntries = true)
    public void updateBook(Book book){
        bookRepository.save(book);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }
}
