package org.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String author_first_name;
    @NotBlank
    private String author_last_name;

    public Author(){
        super();
    }
    public Author(Long id, String author_first_name, String author_last_name) {
        super();
        this.id = id;
        this.author_first_name = author_first_name;
        this.author_last_name = author_last_name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthor_first_name() {
        return author_first_name;
    }
    public void setAuthor_first_name(String author_first_name) {
        this.author_first_name = author_first_name;
    }
    public String getAuthor_last_name() {
        return author_last_name;
    }
    public void setAuthor_last_name(String author_last_name) {
        this.author_last_name = author_last_name;
    }


}
