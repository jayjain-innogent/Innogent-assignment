package com.jay.libraryManagement.service.impl;

import com.jay.libraryManagement.dao.MemberDao;
import com.jay.libraryManagement.dao.impl.MemberDoaImpl;
import com.jay.libraryManagement.dto.MemberRequestDto;
import com.jay.libraryManagement.dto.MemberResponseWithoutbooksDto;
import com.jay.libraryManagement.entity.Member;
import com.jay.libraryManagement.exception.NoDataFoundException;
import com.jay.libraryManagement.mapper.MemberMapper;
import com.jay.libraryManagement.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class MemberServiceImpl implements MemberService {


    private MemberDoaImpl memberDao;

    @Autowired
    public MemberServiceImpl(MemberDoaImpl memberDao){
        this.memberDao = memberDao;
    }
    //CREATE

    @Transactional
    public MemberResponseWithoutbooksDto createMember(MemberRequestDto memberRequestDto){
        Member member = MemberMapper.mapToMember(memberRequestDto);
        memberDao.createMember(member);
        return MemberMapper.mapToBookResponseWithoutAuthor(member);
    }

    public List<MemberResponseWithoutbooksDto> fetchAllData(){
        List<Member> memberList= memberDao.fetchData();
        if(memberList.isEmpty()) {
            throw new NoDataFoundException("No members found");
        }
        List<MemberResponseWithoutbooksDto> data = new ArrayList<>();
        for(Member m: memberList){
            MemberResponseWithoutbooksDto dto = MemberMapper.mapToBookResponseWithoutAuthor(m);
            data.add(dto);
        }
        return data;
    }


    // DELETE BY ID
    @Transactional
    public MemberResponseWithoutbooksDto deleteDataByID(Long id){
        Member member = memberDao.fetchDataById(id);
        memberDao.deleteDataById(id);
        return MemberMapper.mapToBookResponseWithoutAuthor(member);
    }

    //UPDATE
    @Transactional
    public MemberResponseWithoutbooksDto updateDataById(MemberRequestDto memberRequestDto, Long id){
        Member member = memberDao.fetchDataById(id);
        if (memberRequestDto.getName() != null){
            member.setName(memberRequestDto.getName());
        }
        memberDao.updateDataById(member);
        return MemberMapper.mapToBookResponseWithoutAuthor(member);
    }

    public MemberResponseWithoutbooksDto fetchDataByID(Long id){
        Member member = memberDao.fetchDataById(id);
        return MemberMapper.mapToBookResponseWithoutAuthor(member);
    }
    //Adding multiple data
    @Transactional
    public List<MemberResponseWithoutbooksDto> addMultiBooks(List<MemberRequestDto> members){
        List<Member> memberList = new ArrayList<>();
        for(MemberRequestDto m : members){
            Member new_member = MemberMapper.mapToMember(m);
            memberList.add(new_member);
        }
        memberDao.addMltiple(memberList);
        List<MemberResponseWithoutbooksDto> memberDto = new ArrayList<>();
        for(Member m: memberList){
            MemberResponseWithoutbooksDto memberdto = MemberMapper.mapToBookResponseWithoutAuthor(m);
            memberDto.add(memberdto);
        }
        return memberDto;
    }
}
