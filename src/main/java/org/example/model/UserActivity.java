package org.example.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "userActivities")
public class UserActivity {
    @Id
    @GeneratedValue()
    private Long id;
   // @NotBlank
    private LocalDateTime dateAndTime;
    //@NotBlank
    private String message;

    @ManyToOne()
    @JoinColumn(name = "user")
    private User user;

    public UserActivity(){
        super();
    }

    public UserActivity(LocalDateTime dateAndTime, String message, User user) {
        super();
        this.dateAndTime = dateAndTime;
        this.message = message;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}