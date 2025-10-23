package com.jay.libraryManagement.service.impl;

import com.jay.libraryManagement.dao.impl.AuthorDaoImpl;
import com.jay.libraryManagement.dto.AuthorRequestDto;
import com.jay.libraryManagement.dto.AuthorResponseWithoutBooks;
import com.jay.libraryManagement.entity.Author;
import com.jay.libraryManagement.mapper.AuthorMapper;
import com.jay.libraryManagement.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDaoImpl authorDao;

    public AuthorServiceImpl(AuthorDaoImpl authorDao){
        this.authorDao = authorDao;
    }

    @Override
    @Transactional
    public AuthorResponseWithoutBooks createAuthor(AuthorRequestDto authorRequestDto) {
        Author author = AuthorMapper.mapToAuthor(authorRequestDto);
        authorDao.createAuthor(author);
        return AuthorMapper.mapToAuthorResponseWithoutBook(author);
    }



    public List<AuthorResponseWithoutBooks> fetchData(){
        List<Author> authorList = authorDao.fetchData();
        List<AuthorResponseWithoutBooks> data = new ArrayList<>();

        for(Author a : authorList){
            AuthorResponseWithoutBooks dto = AuthorMapper.mapToAuthorResponseWithoutBook(a);
            data.add(dto);
        }

        return data;
    }

    @Override
    public AuthorResponseWithoutBooks deleteDataById(Long id){
        Author data = authorDao.deleteDataById(id);
        return AuthorMapper.mapToAuthorResponseWithoutBook(data);
    }

    //UPDATE
    @Override
    @Transactional
    public AuthorResponseWithoutBooks updateDataById(AuthorRequestDto author, Long id){
        Author data = authorDao.updateDataById(author, id);
        return AuthorMapper.mapToAuthorResponseWithoutBook(data);
    }
}
