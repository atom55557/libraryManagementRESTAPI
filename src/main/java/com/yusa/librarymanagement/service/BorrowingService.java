package com.yusa.librarymanagement.service;

import com.yusa.librarymanagement.dto.BorrowingRequestDto;
import com.yusa.librarymanagement.enums.BookStatus;
import com.yusa.librarymanagement.enums.Status;
import com.yusa.librarymanagement.model.Book;
import com.yusa.librarymanagement.model.Borrowing;
import com.yusa.librarymanagement.model.User;
import com.yusa.librarymanagement.repository.BorrowingRepository;
import com.yusa.librarymanagement.service.impl.BorrowingServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService implements BorrowingServiceInterface {
    private final BorrowingRepository borrowingRepository;
    private final UserService userService;
    private final BookService bookService;


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
        return borrowingRepository.save(borrowing);

    }

    public Borrowing getBorrowingById(long borrowingId){
        return borrowingRepository.findById(borrowingId).orElseThrow();
    }

    public void deleteBorrowing(Long id){
        Borrowing borrowing1 = borrowingRepository.findById(id).orElseThrow();
        borrowingRepository.delete(borrowing1);
    }
}
