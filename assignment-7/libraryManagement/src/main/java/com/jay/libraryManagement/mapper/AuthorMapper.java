package com.jay.libraryManagement.mapper;


import com.jay.libraryManagement.dto.AuthorRequestDto;
import com.jay.libraryManagement.dto.AuthorResponseWithoutBooks;
import com.jay.libraryManagement.entity.Author;

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
}
