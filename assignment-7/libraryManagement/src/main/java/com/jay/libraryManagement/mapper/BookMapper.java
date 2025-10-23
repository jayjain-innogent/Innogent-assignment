package com.jay.libraryManagement.mapper;

import com.jay.libraryManagement.dto.BookRequestDto;
import com.jay.libraryManagement.dto.BookResponseWithoutAuthorDto;
import com.jay.libraryManagement.entity.Book;

public class BookMapper {
    public static Book mapToBook(BookRequestDto bookRequestDto){
        return Book.builder()
                .title(bookRequestDto.getTitle())
                .stock(bookRequestDto.getStock())
                .build();
    }

    public static BookResponseWithoutAuthorDto mapToBookResponseWithoutAuthor(Book book){
        return new BookResponseWithoutAuthorDto(
                book.getId(),
                book.getTitle()
        );
    }
}
