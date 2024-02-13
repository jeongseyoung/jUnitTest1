package com.example.junittest1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.junittest1.dto.MemberDto;
import com.example.junittest1.entity.Board;
import com.example.junittest1.entity.Member;
import com.example.junittest1.service.MainService;

import lombok.RequiredArgsConstructor;

/**
 * MainController
 */
@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("/")
    public void main() {
        System.out.println("main");
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<MemberDto> add(@RequestBody MemberDto mDto) {
        System.out.println("mDto:(Controller) " + mDto);
        return new ResponseEntity<MemberDto>(mainService.addMember(mDto), HttpStatus.CREATED);
    }

    @PostMapping("/getMember")
    public ResponseEntity<MemberDto> getMember(@RequestBody Member m) {
        return new ResponseEntity<MemberDto>(mainService.getMember(m), HttpStatus.OK);
    }

    @GetMapping("/getAllMembers")
    public ResponseEntity<List<MemberDto>> getAllMemberDto() {
        System.out.println("getAllMembers controller");
        return new ResponseEntity<List<MemberDto>>(mainService.getAllMembers(), HttpStatus.OK);
    }

    @GetMapping("/deletemember")
    public ResponseEntity<?> deleteMember(int id) {
        mainService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.OK).body(id + " has/have been deleted");
    }

    @PatchMapping("/memberupdate")
    public ResponseEntity<MemberDto> updateMember(long Id, @RequestBody MemberDto memberDto) {
        int memberId = (int) Id;
        System.out.println("ResponseEntity<MemberDto>: ");
        return new ResponseEntity<MemberDto>(mainService.memberupdate(memberId, memberDto), HttpStatus.OK);
    }

    // @PostMapping("checklist")
    // public ResponseEntity<List<Board>> postMethodName() {
    // // TODO: process POST request

    // return new ResponseEntity<List<Board>>(mainService.getlist(), HttpStatus.OK);
    // }

}