package com.yusa.librarymanagement.repository;

import com.yusa.librarymanagement.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing,Long> {
    List<Borrowing> findByUserId(Long userId);

    List<Borrowing> findByBookId(Long bookId);
}
