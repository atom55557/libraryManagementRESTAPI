package com.yusa.librarymanagement.service;

import com.yusa.librarymanagement.dto.BorrowingRequestDto;
import com.yusa.librarymanagement.dto.BorrowingUserRequestDto;
import com.yusa.librarymanagement.enums.BookStatus;
import com.yusa.librarymanagement.enums.Status;
import com.yusa.librarymanagement.model.Book;
import com.yusa.librarymanagement.model.Borrowing;
import com.yusa.librarymanagement.model.User;
import com.yusa.librarymanagement.repository.BorrowingRepository;
import com.yusa.librarymanagement.service.impl.BorrowingServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService implements BorrowingServiceInterface {
    private final BorrowingRepository borrowingRepository;
    private final UserService userService;
    private final BookService bookService;


    @Transactional
    public Borrowing create(BorrowingRequestDto request){
        User user = userService.getUserById(request.getUserId());
        Book book = bookService.getBookById(request.getBookId());

        if(book.getBookStatus().equals(BookStatus.MUSAIT)){
            Borrowing borrowing = new Borrowing();
            borrowing.setUser(user);
            borrowing.setBook(book);
            book.setBookStatus(BookStatus.ODUNCTE);
            bookService.updateBook(book);
            borrowing.setStatus(Status.BORROWED);

            return borrowingRepository.save(borrowing) ;
        }

        throw new IllegalStateException("Illegal argument: " + request.getBookId());
    }

    public List<Borrowing> getAllBorrowing(){
        return borrowingRepository.findAll();
    }

    public List<Borrowing> getBorrowingByUserId( Long userId){
        return borrowingRepository.findByUserId(userId);
    }

    public List<Borrowing> getBorrowingByBookId(Long bookId){
        return borrowingRepository.findByBookId(bookId);
    }

    public Borrowing returnBorrowing(long borrowingId){
        Borrowing borrowing = getBorrowingById(borrowingId);
        borrowing.setStatus(Status.RETURNED);
        Book book = bookService.getBookById(borrowing.getBook().getId());
        book.setBookStatus(BookStatus.MUSAIT);
        bookService.updateBook(book);
        return borrowingRepository.save(borrowing);

    }

    @Transactional
    public Borrowing returnBorrowingUser(long bookId){
        Book book = bookService.getBookById(bookId);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        Borrowing existingBorrowing = borrowingRepository.findByUserIdAndBookIdAndStatus(
                user.getId(), book.getId(), Status.BORROWED );
        if(existingBorrowing == null){
            throw new IllegalStateException("User has not borrowed this book or already returned");
        }
        book.setBookStatus(BookStatus.MUSAIT);
        bookService.updateBook(book);
        existingBorrowing.setStatus(Status.RETURNED);
        return borrowingRepository.save(existingBorrowing);
    }


    public Borrowing getBorrowingById(long borrowingId){
        return borrowingRepository.findById(borrowingId).orElseThrow();
    }

    public void deleteBorrowing(Long id){
        Borrowing borrowing1 = borrowingRepository.findById(id).orElseThrow();
        borrowingRepository.delete(borrowing1);
    }

    public void valitadeUserBorrowingStatus(long userId){
        long activeBorrowCount = borrowingRepository.countByUserIdAndStatus(userId, Status.BORROWED);
        if(activeBorrowCount >= 1){
            throw new IllegalStateException("User already Borrowed a book!");
        }
    }

    @Transactional
    public Borrowing userCreateBorrowing(BorrowingUserRequestDto request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Book book = bookService.getBooksByName(request.getBookName()).get(0);
        User user = userService.findUserByEmail(email);

        valitadeUserBorrowingStatus(user.getId());

        if(book.getBookStatus().equals(BookStatus.MUSAIT)){
            Borrowing borrowing = new Borrowing();
            borrowing.setUser(user);
            borrowing.setBook(book);
            book.setBookStatus(BookStatus.ODUNCTE);
            bookService.updateBook(book);
            borrowing.setStatus(Status.BORROWED);
            return borrowingRepository.save(borrowing);
        }
        throw new IllegalStateException("Book already borrowed!");
    }

    public List<Borrowing> getMyBorrowings() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        return getBorrowingByUserId(user.getId());
    }
}
