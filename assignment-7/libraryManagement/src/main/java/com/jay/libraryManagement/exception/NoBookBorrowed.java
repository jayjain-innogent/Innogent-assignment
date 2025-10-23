package com.jay.libraryManagement.exception;

public class NoBookBorrowed extends RuntimeException{
    public NoBookBorrowed(String message){
        super(message);
    }
}
