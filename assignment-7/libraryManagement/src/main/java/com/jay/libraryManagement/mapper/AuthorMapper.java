package com.jay.libraryManagement.mapper;


import com.jay.libraryManagement.dto.AuthorRequestDto;
import com.jay.libraryManagement.dto.AuthorResponseDto;
import com.jay.libraryManagement.dto.AuthorResponseWithoutBooks;
import com.jay.libraryManagement.dto.BookResponseWithoutAuthorDto;
import com.jay.libraryManagement.entity.Author;
import com.jay.libraryManagement.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {

    public static AuthorResponseWithoutBooks mapToAuthorResponseWithoutBook(Author author){

        return new AuthorResponseWithoutBooks(
                author.getId(),
                author.getName()
        );
    }


    public static AuthorResponseWithoutBooks mapToAuthorResponseWithoutBook(AuthorRequestDto author){
        Author newAuthor = mapToAuthor(author);
        return new AuthorResponseWithoutBooks(
                newAuthor.getId(),
                newAuthor.getName()
        );
    }

    public static Author mapToAuthor(AuthorRequestDto authorRequestDto){
        return Author.builder()
                .name(authorRequestDto.getName())
                .build();
    }

    public static AuthorResponseDto mapToAuthorResponse(Author author){

        List<String> books = new ArrayList<>();

        if(!author.getBooks().isEmpty()){
            List<Book> bookList = author.getBooks();
            for (Book b: bookList){
                BookResponseWithoutAuthorDto dto = BookMapper.mapToBookResponseWithoutAuthor(b);
                books.add(dto.getTitle());
            }
        }

        return new AuthorResponseDto(
                author.getId(),
                author.getName(),
                books
        );
    }
}
