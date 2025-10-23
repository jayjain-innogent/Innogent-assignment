package com.jay.libraryManagement.dao.impl;

import com.jay.libraryManagement.dao.AuthorDao;
import com.jay.libraryManagement.dto.AuthorRequestDto;
import com.jay.libraryManagement.entity.Author;
import com.jay.libraryManagement.exception.NoDataFoundException;
import com.jay.libraryManagement.exception.ResourceNotFoundException;
import com.jay.libraryManagement.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;
    public AuthorDaoImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    //CREATE
    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }

    //FETCH
    public List<Author> fetchData(){
        List<Author> data = authorRepository.findAll();
        if(data.isEmpty()){
            throw new NoDataFoundException("No Record Found");
        }
        return data;
    }

    //DELETE
    public Author deleteDataById(Long id){
        Author data = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No data found "+id)
        );

        authorRepository.deleteById(id);
        return data;
    }

    //UPDATE
    public Author updateDataById(AuthorRequestDto author, Long id){
        Author data = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No data found "+id)
        );

        if(author.getName() != null){
            data.setName(author.getName());
        }

        authorRepository.save(data);
        return data;

    }

    //FIND BY ID
    public Author findAuthorById(Long id){
        return authorRepository.findById(id).get();
    };


}
