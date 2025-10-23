package com.jay.libraryManagement.service;

import com.jay.libraryManagement.dto.BookRequestDto;
import com.jay.libraryManagement.dto.BookResponseDto;
import com.jay.libraryManagement.dto.BookResponseWithoutAuthorDto;
import com.jay.libraryManagement.entity.Book;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BookService {
    @Transactional
    public BookResponseWithoutAuthorDto createBook(BookRequestDto book);
    public List<BookResponseWithoutAuthorDto> fetchData();
    @Transactional
    public BookResponseWithoutAuthorDto deleteDataById(Long id);
    @Transactional
    public BookResponseWithoutAuthorDto updateDataById(BookRequestDto book, Long id);
    public BookResponseWithoutAuthorDto fetchDataById(Long id);
    @Transactional
    public List<BookResponseWithoutAuthorDto> addMultiBooks(List<BookRequestDto> books);
}
