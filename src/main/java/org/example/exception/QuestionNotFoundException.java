package org.example.exception;

public class QuestionNotFoundException extends Exception{
    public QuestionNotFoundException() {
        super("No such Question");
    }

}
