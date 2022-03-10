package org.example.repository;

import org.example.exception.EmailNotFoundException;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT a FROM User a WHERE a.email = :email")
    User findByEmail(String email) throws EmailNotFoundException;

    @Query("SELECT a.nationality, COUNT(a.nationality) AS magnitude FROM User a GROUP BY a.nationality ORDER BY magnitude DESC")
    List<String> getMostCommonNationality();

    @Query("SELECT a.ageGroup, COUNT(a.ageGroup) AS magnitude FROM User a GROUP BY a.ageGroup ORDER BY magnitude DESC")
    List<String> getMostCommonAgeGroup();

    //SELECT (IFNull(sex,'Total')) AS sex, COUNT(id) AS Count FROM users GROUP BY sex WITH rollup; -- returns total
    // SELECT sex, COUNT(id) AS Count FROM users GROUP BY sex;
    @Query("SELECT a.sex, COUNT(a.id) AS Count FROM User a GROUP BY a.sex ORDER BY a.sex DESC")  // always male first
    List<String> getGenderCounts();
}
