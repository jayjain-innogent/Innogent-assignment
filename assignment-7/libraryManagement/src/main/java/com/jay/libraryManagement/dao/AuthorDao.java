package com.jay.libraryManagement.dao;

import com.jay.libraryManagement.dto.AuthorRequestDto;
import com.jay.libraryManagement.entity.Author;

import java.util.List;

public interface AuthorDao {
    public Author createAuthor(Author author);
    public List<Author> fetchData();
    public Author deleteDataById(Long id);
    public Author updateDataById(AuthorRequestDto author, Long id);
    public Author findAuthorById(Long id);
}
