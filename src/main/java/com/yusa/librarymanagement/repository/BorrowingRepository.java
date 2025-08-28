package com.yusa.librarymanagement.repository;

import com.yusa.librarymanagement.enums.Status;
import com.yusa.librarymanagement.model.Borrowing;
import com.yusa.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing,Long> {
    List<Borrowing> findByUserId(Long userId);

    List<Borrowing> findByBookId(Long bookId);

    List<Borrowing> user(User user);

    long countByUserIdAndStatus(long userId, Status status);

    Borrowing findByUserIdAndBookIdAndStatus(Long id, long id1, Status status);
}
