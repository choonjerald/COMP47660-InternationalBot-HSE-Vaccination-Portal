package org.example.repository;

import org.example.exception.EmailNotFoundException;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT a FROM User a WHERE a.email = :email")
    User findByEmail(String email) throws EmailNotFoundException;
}
