package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "authorship")
@IdClass(AuthorshipId.class)
public class Authorship {
    @Id
    @NotBlank
    private Long id_book;

    @Id
    @NotBlank
    private Long id_author;


    public Authorship(){
        super();
    }
    public Authorship(Long id_book, Long id_author) {
        super();
        this.id_book = id_book;
        this.id_author = id_author;
    }

    public Long getId_book() {
        return id_book;
    }
    public void setId_book(Long id_book) {
        this.id_book = id_book;
    }

    public Long getId_author() {
        return id_author;
    }
    public void setId_author(Long id_author) {
        this.id_author = id_author;
    }



}
