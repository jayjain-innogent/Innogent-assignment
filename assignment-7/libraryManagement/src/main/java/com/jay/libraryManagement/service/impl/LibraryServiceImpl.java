package com.jay.libraryManagement.service.impl;

import com.jay.libraryManagement.dao.impl.BookDoaImpl;
import com.jay.libraryManagement.dao.impl.MemberDoaImpl;
import com.jay.libraryManagement.dto.LibraryTransactionDto;
import com.jay.libraryManagement.entity.Book;
import com.jay.libraryManagement.entity.Member;
import com.jay.libraryManagement.exception.InsufficientStockException;
import com.jay.libraryManagement.exception.NoBookBorrowed;
import com.jay.libraryManagement.exception.ResourceNotFoundException;
import com.jay.libraryManagement.service.LibraryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private MemberDoaImpl memberDoa;
    private BookDoaImpl bookDoa;


    @Autowired
    public LibraryServiceImpl(MemberDoaImpl memberDoa, BookDoaImpl bookDoa){
        this.memberDoa = memberDoa;
        this.bookDoa = bookDoa;
    }

    @Transactional
    @Override
    public String borrowBook(LibraryTransactionDto borrow){

        //Fetaching Data

        Member member = memberDoa.fetchDataById(borrow.getMemberId());
        Book book = bookDoa.fetchDataById(borrow.getBookId());

        //Checking Null values
        if (member == null) {
            throw new ResourceNotFoundException("Member not found with id: " + borrow.getMemberId());
        }
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with id: " + borrow.getBookId());
        }

        //Checking if member have already this book
        if(member.getBorrowedBooks().contains(book)){
            return "This Book is Already Borrowed id: " + member.getId();
        }

        //Checking book stocks
        if (book.getStock() < 1){
            throw new InsufficientStockException("Book "+book.getTitle()+" is out of stocks");
        }

        //Dec Stocks
        book.setStock(book.getStock()-1);

        //Adding the book in member BorroweBokes
        member.getBorrowedBooks().add(book);

        bookDoa.createBook(book);
        memberDoa.createMember(member);

        return "Book '" + book.getTitle() + "' successfully borrowed by " + member.getName();
    }

    @Override
    @Transactional
    public List<String> borrowBooks(List<LibraryTransactionDto> borrows){
        List<String> messages = new ArrayList<>();
        for(LibraryTransactionDto borrow: borrows){
            //Geting Member and Book
            Member member = memberDoa.fetchDataById(borrow.getMemberId());
            if (member == null) {
                messages.add("Member not found with id: " + borrow.getMemberId());
                continue;
            }

            Book book = bookDoa.fetchDataById(borrow.getBookId());
            if (book == null) {
                messages.add("Book not found with id: " + borrow.getBookId());
                continue;
            }

            //Checking Book Is Already Borrowes
            if(member.getBorrowedBooks().contains(book)){
                messages.add("Book is Already Borrowed id: " + member.getId());
                continue;
            }

            //checking stocks
            if (book.getStock() < 1) {
                messages.add("Book '" + book.getTitle() + "' is out of stock.");
                continue;
            }

            //Decrease Stock
            book.setStock(book.getStock() - 1);

            //adding book in member
            member.getBorrowedBooks().add(book);

            //save both member and book
            bookDoa.createBook(book);
            memberDoa.createMember(member);

            //adding msg
            messages.add("Book '" + book.getTitle() + "' successfully borrowed by " + member.getName());
        }

        return messages;
    }

    @Override
    @Transactional
    public String returnBook(LibraryTransactionDto returnBook){

        Member member = memberDoa.fetchDataById(returnBook.getMemberId());
        Book book = bookDoa.fetchDataById(returnBook.getBookId());

        if (member == null) {
            throw new ResourceNotFoundException("Member not found with id: " + returnBook.getMemberId());
        }
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with id: " + returnBook.getBookId());
        }


        if (!member.getBorrowedBooks().contains(book)){
            throw new NoBookBorrowed("Book '" + book.getTitle() + "' is not borrowed by " + member.getName() + " (id: " + member.getId() + ")");

        }

        member.getBorrowedBooks().remove(book);

        book.setStock(book.getStock()+1);

        bookDoa.createBook(book);
        memberDoa.createMember(member);

        return "Book '" + book.getTitle() + "' successfully returned by " + member.getName();

    }


}
