package com.yusa.librarymanagement.service.impl;

import com.yusa.librarymanagement.dto.BorrowingRequestDto;

import com.yusa.librarymanagement.model.Borrowing;

import java.util.List;

public interface BorrowingServiceInterface {
    Borrowing create(BorrowingRequestDto borrowingRequestDto);
    List<Borrowing> getAllBorrowing();
    List<Borrowing> getBorrowingByUserId(Long userId);
    List<Borrowing> getBorrowingByBookId(Long bookId);
    Borrowing returnBorrowing(long borrowingId);
    Borrowing getBorrowingById(long borrowingId);
    void deleteBorrowing(Long borrowingId);

}
