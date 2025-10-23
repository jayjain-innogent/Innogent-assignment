package com.jay.libraryManagement.service;

import com.jay.libraryManagement.dto.LibraryTransactionDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface LibraryService {

    @Transactional
    public String borrowBook(LibraryTransactionDto borrow);

    @Transactional
    public List<String> borrowBooks(List<LibraryTransactionDto> borrows);

    @Transactional
    public String returnBook(LibraryTransactionDto libraryTransactionDto);
}
