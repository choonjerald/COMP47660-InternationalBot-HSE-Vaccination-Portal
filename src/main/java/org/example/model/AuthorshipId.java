package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class AuthorshipId implements Serializable {

    Long id_book;

    Long id_author;

    public AuthorshipId(){}

    public AuthorshipId( Long id_book, Long id_author){
        this.id_author = id_author;
        this.id_book = id_book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorshipId authorshipId = (AuthorshipId) o;
        return id_book.equals(authorshipId.id_book) &&
                id_author.equals(authorshipId.id_author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_book, id_author);
    }

    public Long getId_Author(){
        return id_author;
    }
    public Long getId_Book(){
        return id_book;
    }
}
