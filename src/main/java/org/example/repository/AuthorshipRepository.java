package org.example.repository;

import org.example.exception.AuthorNotFoundException;
import org.example.model.Authorship;
import org.example.model.Author;
import org.example.model.AuthorshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorshipRepository extends JpaRepository<Authorship, AuthorshipId> {

    @Query("select a.id_author from Authorship a where a.id_book = ?1")
    List<Long> findAuthorByBookId(Long book_id) throws AuthorNotFoundException;


}



