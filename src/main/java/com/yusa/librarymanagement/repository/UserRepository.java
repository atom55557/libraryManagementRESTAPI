package com.yusa.librarymanagement.repository;

import com.yusa.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUserName(String name);

    User finUserByEmail(String email);
}
