package com.jay.libraryManagement.dao;

import com.jay.libraryManagement.dto.AuthorRequestDto;
import com.jay.libraryManagement.dto.BookRequestDto;
import com.jay.libraryManagement.entity.Author;
import com.jay.libraryManagement.entity.Book;

import java.util.List;

public interface BookDao {
    public void createBook(Book book);
    public List<Book> fetchData();
    public void deleteDataById(Long id);
    public void updateDataById(Book book);
    public Book fetchDataById(Long id);
    public List<Book> addMultiBooks(List<Book> book);
}
