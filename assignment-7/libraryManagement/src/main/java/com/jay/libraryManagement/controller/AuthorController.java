package com.jay.libraryManagement.controller;

import com.jay.libraryManagement.dto.AuthorRequestDto;
import com.jay.libraryManagement.dto.AuthorResponseDto;
import com.jay.libraryManagement.dto.AuthorResponseWithoutBooks;
import com.jay.libraryManagement.service.impl.AuthorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService){
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public ResponseEntity<AuthorResponseWithoutBooks> createAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        return new ResponseEntity<>(authorService.createAuthor(authorRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/fetch-all-data")
    public ResponseEntity<List<AuthorResponseWithoutBooks>> getData(){
        return new ResponseEntity<>(authorService.fetchData(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AuthorResponseWithoutBooks> deleteDataById(@PathVariable Long id){
        return new ResponseEntity<>(authorService.deleteDataById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorResponseWithoutBooks> updateDataById(@RequestBody AuthorRequestDto author,@PathVariable Long id){
        return new ResponseEntity<>(authorService.updateDataById(author, id), HttpStatus.OK);
    }

    // Get all books along with their author details
    @GetMapping("/get-author-and-books")
    public ResponseEntity<List<AuthorResponseDto>> fetchDataWithBook(){
        return new ResponseEntity<>(authorService.fetchDataWithBook(), HttpStatus.OK);
    }

}
