package com.jay.libraryManagement.service;

import com.jay.libraryManagement.dto.BookRequestDto;
import com.jay.libraryManagement.dto.BookResponseWithoutAuthorDto;
import com.jay.libraryManagement.dto.MemberRequestDto;
import com.jay.libraryManagement.dto.MemberResponseWithoutbooksDto;
import com.jay.libraryManagement.repository.MemberRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public interface MemberService {
    @Transactional
    public MemberResponseWithoutbooksDto createMember(MemberRequestDto member);
    public List<MemberResponseWithoutbooksDto> fetchAllData();
    @Transactional
    public MemberResponseWithoutbooksDto deleteDataByID(Long id);
    @Transactional
    public MemberResponseWithoutbooksDto updateDataById(MemberRequestDto member, Long id);
    public MemberResponseWithoutbooksDto fetchDataByID(Long id);
    @Transactional
    public List<MemberResponseWithoutbooksDto> addMultiBooks(List<MemberRequestDto> member);
}
