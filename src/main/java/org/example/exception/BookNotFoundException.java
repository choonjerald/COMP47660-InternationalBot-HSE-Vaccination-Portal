package org.example.exception;

public class BookNotFoundException extends Exception {
    private Long book_id;
    public BookNotFoundException(Long book_id) {
        super(String.format("Book is not found with id : '%s'", book_id));
    }
}
