package org.example.exception;


public class AuthorNotFoundException extends Exception{
    private long author_id;
    public AuthorNotFoundException(long author_id) {
        super(String.format("Author is not found with id : '%s'", author_id));
    }

}
