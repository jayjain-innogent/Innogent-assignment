package com.jay.libraryManagement.controller;
import java.util.*;

import com.jay.libraryManagement.dto.MemberRequestDto;
import com.jay.libraryManagement.dto.MemberResponseWithoutbooksDto;
import com.jay.libraryManagement.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;

    //MULTIPLE
    @PostMapping("/add-multiple")
    public ResponseEntity<List<MemberResponseWithoutbooksDto>> addMultiple(@RequestBody List<MemberRequestDto> memberRequestDtos){
        return new ResponseEntity<>(memberService.addMultiBooks(memberRequestDtos), HttpStatus.CREATED);
    }
    //READ
    @GetMapping("/get-all-data")
    public ResponseEntity<List<MemberResponseWithoutbooksDto>> fetchData(){
        return new ResponseEntity<>(memberService.fetchAllData(), HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MemberResponseWithoutbooksDto> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(memberService.deleteDataByID(id), HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<MemberResponseWithoutbooksDto> updateById(@PathVariable Long id, @RequestBody MemberRequestDto member){
        return new ResponseEntity<>(memberService.updateDataById(member ,id), HttpStatus.OK);
    }


    //FINDBYID
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<MemberResponseWithoutbooksDto> fetchById(@PathVariable Long id){
        return new ResponseEntity<>(memberService.fetchDataByID(id), HttpStatus.OK);
    }
}
