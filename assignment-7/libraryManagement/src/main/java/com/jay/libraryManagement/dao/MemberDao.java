package com.jay.libraryManagement.dao;


import com.jay.libraryManagement.dto.MemberResponseWithoutbooksDto;
import com.jay.libraryManagement.entity.Member;

import java.util.List;

public interface MemberDao {
    public void createMember(Member member);
    public List<Member> fetchData();
    public void deleteDataById(Long id);
    public void updateDataById(Member member);
    public void addMltiple(List<Member> members);

    public Member fetchDataById(Long id);
}
