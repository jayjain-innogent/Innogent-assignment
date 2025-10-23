package com.jay.libraryManagement.service.impl;

import com.jay.libraryManagement.dao.impl.AuthorDaoImpl;
import com.jay.libraryManagement.dao.impl.BookDoaImpl;
import com.jay.libraryManagement.dto.BookRequestDto;
import com.jay.libraryManagement.dto.BookResponseWithoutAuthorDto;
import com.jay.libraryManagement.entity.Author;
import com.jay.libraryManagement.entity.Book;
import com.jay.libraryManagement.mapper.BookMapper;
import com.jay.libraryManagement.service.BookService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDoaImpl bookDao;

    @Autowired
    private AuthorDaoImpl authorDao;


    //CREATE
    @Transactional
    public BookResponseWithoutAuthorDto createBook(BookRequestDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        Author author = authorDao.findAuthorById(bookDto.getAuthorId());
        book.setAuthor(author);
        bookDao.createBook(book);
        return BookMapper.mapToBookResponseWithoutAuthor(book);
    }



    //READ
    public List<BookResponseWithoutAuthorDto> fetchData(){
        List<Book> books = bookDao.fetchData();
        List<BookResponseWithoutAuthorDto> book_dto = new ArrayList<>();

        for(Book b: books){
            BookResponseWithoutAuthorDto dto = BookMapper.mapToBookResponseWithoutAuthor(b);
            book_dto.add(dto);
        }

        return book_dto;
    }

    //DELETE
    @Transactional
    public BookResponseWithoutAuthorDto deleteDataById(Long id){

        Book book = bookDao.fetchDataById(id);
        bookDao.deleteDataById(id);
        return BookMapper.mapToBookResponseWithoutAuthor(book);
    }

    //UPDATE
    @Transactional
    public BookResponseWithoutAuthorDto updateDataById(BookRequestDto bookDto, Long id){
        Book book = bookDao.fetchDataById(id);
        if(bookDto.getTitle() != null){
            book.setTitle(bookDto.getTitle());
        }
        if(bookDto.getStock() != null){
            book.setStock(bookDto.getStock());
        }
        if(bookDto.getAuthorId() != null){
            Author author = authorDao.findAuthorById(id);
            book.setAuthor(author);
        }
        bookDao.createBook(book);
        return BookMapper.mapToBookResponseWithoutAuthor(book);
    }

    //FIND BOOK BY ID
    public BookResponseWithoutAuthorDto fetchDataById(Long id){
        Book book = bookDao.fetchDataById(id);
        return BookMapper.mapToBookResponseWithoutAuthor(book);
    }

    //ADD MULTIPLE DATA
    @Transactional
    public List<BookResponseWithoutAuthorDto> addMultiBooks(List<BookRequestDto> booksDto){
        List<Book> books = new ArrayList<>();
        for(BookRequestDto b : booksDto){
            Book book = BookMapper.mapToBook(b);
            Author author = authorDao.findAuthorById(b.getAuthorId());
            book.setAuthor(author);
            books.add(book);
        }
        bookDao.addMultiBooks(books);
        List<BookResponseWithoutAuthorDto> booksRes = new ArrayList<>();

        for(Book b : books){
            BookResponseWithoutAuthorDto book = BookMapper.mapToBookResponseWithoutAuthor(b);
            booksRes.add(book);
        }
        return booksRes;
    }
}

