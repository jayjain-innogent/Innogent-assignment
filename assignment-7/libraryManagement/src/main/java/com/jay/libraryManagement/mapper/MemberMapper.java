package com.jay.libraryManagement.mapper;

import com.jay.libraryManagement.dto.BookRequestDto;
import com.jay.libraryManagement.dto.BookResponseWithoutAuthorDto;
import com.jay.libraryManagement.dto.MemberRequestDto;
import com.jay.libraryManagement.dto.MemberResponseWithoutbooksDto;
import com.jay.libraryManagement.entity.Book;
import com.jay.libraryManagement.entity.Member;

public class MemberMapper {
    public static Member mapToMember(MemberRequestDto memberRequestDto){
        return Member.builder()
                .name(memberRequestDto.getName())
                .build();
    }

    public static MemberResponseWithoutbooksDto mapToBookResponseWithoutAuthor(Member member){
        return new MemberResponseWithoutbooksDto(
                member.getId(),
                member.getName()
        );
    }
}
