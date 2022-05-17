package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 254, message = "Title is too long")
    private String title;

    @NotBlank(message = "Details cannot be empty")
    @Size(max = 254, message = "Details is too long")
    private String details;

    @Size(max = 254, message = "Details is too long")
    private String answers;

    private boolean status;

    public Question() {
    }

    public Question(Long id, String title, String details, String answers, boolean status) {
        super();
        this.id = id;
        this.title = title;
        this.details = details;
        this.answers = answers;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
