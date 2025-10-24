package com.jay.libraryManagement.controller;

import com.jay.libraryManagement.dto.LibraryTransactionDto;
import com.jay.libraryManagement.service.impl.LibraryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryServiceImpl libraryService;


    @PostMapping("/borrow")
    public ResponseEntity<String> singleBorrow(@RequestBody LibraryTransactionDto borrow){
        String message = libraryService.borrowBook(borrow);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @PostMapping("/return-book")
    public ResponseEntity<String> returnBook(@RequestBody LibraryTransactionDto returned){
        String message = libraryService.returnBook(returned);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}

