package com.jay.libraryManagement.dao.impl;

import com.jay.libraryManagement.dao.MemberDao;
import com.jay.libraryManagement.entity.Member;
import com.jay.libraryManagement.exception.ResourceNotFoundException;
import com.jay.libraryManagement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDoaImpl implements MemberDao {

    @Autowired
    private MemberRepository memberRepository;

    public void createMember(Member member){
         memberRepository.save(member);
    }

    public List<Member> fetchData(){
        return memberRepository.findAll();
    }

    public void deleteDataById(Long id){
        memberRepository.deleteById(id);
    }

    public void updateDataById(Member member){
         memberRepository.save(member);
    }

    public void addMltiple(List<Member> members){
         memberRepository.saveAll(members);
    }

    public Member fetchDataById(Long id){
        return memberRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Resouse Found "+id)
        );
    }
}
