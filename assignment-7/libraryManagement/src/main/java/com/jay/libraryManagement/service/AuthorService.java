package com.jay.libraryManagement.service;

import com.jay.libraryManagement.dto.AuthorRequestDto;
import com.jay.libraryManagement.dto.AuthorResponseDto;
import com.jay.libraryManagement.dto.AuthorResponseWithoutBooks;
import com.jay.libraryManagement.entity.Author;
import com.jay.libraryManagement.repository.AuthorRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AuthorService {
    @Transactional
    public AuthorResponseWithoutBooks createAuthor(AuthorRequestDto authorRequestDto);
    public List<AuthorResponseWithoutBooks> fetchData();
    public AuthorResponseWithoutBooks deleteDataById(Long id);
    @Transactional
    public AuthorResponseWithoutBooks updateDataById(AuthorRequestDto author, Long id);
    public List<AuthorResponseDto> fetchDataWithBook();
}
