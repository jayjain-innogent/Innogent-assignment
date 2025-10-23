package com.jay.libraryManagement.controller;

import com.jay.libraryManagement.dto.BookRequestDto;
import com.jay.libraryManagement.dto.BookResponseDto;
import com.jay.libraryManagement.dto.BookResponseWithoutAuthorDto;
import com.jay.libraryManagement.service.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService){
        this.bookService = bookService;
    }

    //ADD BOOK
    @PostMapping("/create")
    public ResponseEntity<BookResponseWithoutAuthorDto> createData(@RequestBody BookRequestDto book){
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    //FETCH DATA
    @GetMapping("/fetch-all-data")
    public ResponseEntity<List<BookResponseWithoutAuthorDto>> fetchData(){
        return new ResponseEntity<>(bookService.fetchData(), HttpStatus.OK);
    }

    //Fetch Data By Id
    @GetMapping("/fetch-data-by-id")
    public ResponseEntity<BookResponseWithoutAuthorDto> fetchDataById(@RequestParam Long id){
        return new ResponseEntity<>(bookService.fetchDataById(id), HttpStatus.OK);
    }

    //UPDATE Data By Id
    @PutMapping("/update-data-by-id/{id}")
    public ResponseEntity<BookResponseWithoutAuthorDto> updateDataById(@RequestBody BookRequestDto book, @PathVariable Long id){
        return new ResponseEntity<>(bookService.updateDataById(book, id), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/delete-data-by-id/{id}")
    public ResponseEntity<BookResponseWithoutAuthorDto> deleteDataById(@PathVariable Long id){
        return new ResponseEntity<>(bookService.deleteDataById(id), HttpStatus.OK);
    }

    //ADD MULTIPLE RECORDS
    @PostMapping("/add-multiple")
    public ResponseEntity<List<BookResponseWithoutAuthorDto>> addMultiple(@RequestBody List<BookRequestDto> books){
        return new ResponseEntity<>(bookService.addMultiBooks(books), HttpStatus.CREATED);
    }
}
