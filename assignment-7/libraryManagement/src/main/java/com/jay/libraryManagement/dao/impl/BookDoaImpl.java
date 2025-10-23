package com.jay.libraryManagement.dao.impl;

import com.jay.libraryManagement.dao.BookDao;
import com.jay.libraryManagement.entity.Book;
import com.jay.libraryManagement.exception.ResourceNotFoundException;
import com.jay.libraryManagement.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDoaImpl implements BookDao {

    private BookRepository bookRepository;

    public BookDoaImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //CREATE
    public void createBook(Book book){
         bookRepository.save(book);
    }

    //READ
    public List<Book> fetchData(){
        return bookRepository.findAll();
    }

    //UPDATE
    public void updateDataById(Book book){
        bookRepository.save(book);
    }

    //DELETE
    public void deleteDataById(Long id){
        bookRepository.deleteById(id);
    }

    //FIND BY ID
    public Book fetchDataById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No data related to "+id)
        );
        return book;
    }

    //ADDING  MULTIPLE DATA AT ONCES
    public List<Book> addMultiBooks(List<Book> books){
        return bookRepository.saveAll(books);
    }
}
