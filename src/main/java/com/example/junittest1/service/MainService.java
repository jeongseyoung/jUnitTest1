package com.example.junittest1.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.junittest1.dto.MemberDto;
import com.example.junittest1.entity.Board;
import com.example.junittest1.entity.Member;
import com.example.junittest1.repository.MainRepository;

import lombok.RequiredArgsConstructor;

/**
 * MainService
 */
@Service
@RequiredArgsConstructor
public class MainService {
    private final MainRepository mainRepository;
    public List<MemberDto> getAllMembers;

    public MemberDto addMember(MemberDto mDto) {
        System.out.println("mDto: " + mDto.getName());
        Member member = Member.builder().name(mDto.getName()).email(mDto.getEmail()).build();
        System.out.println("member: " + member.getName());
        Member newMember = mainRepository.save(member);

        MemberDto memberDto = MemberDto.builder().id(newMember.getId()).name(newMember.getName())
                .email(newMember.getEmail()).build();

        return memberDto;
    }

    public String getMemberName(String name) {
        System.out.println("getMemberName service: " + name);
        return mainRepository.findByname(name).get().getName();
    }

    public MemberDto getMember(Member m) {
        Member member = mainRepository.findByname(m.getName()).get();
        return convertMemberDto(member);
    }

    public MemberDto getOneMember(int id) {
        Member member = mainRepository.findById(id).get();
        return convertMemberDto(member);
    }

    public void deleteMember(int id) {
        System.out.println("delete service:, id: " + id);
        mainRepository.deleteById(id);
        // return "delete OK";
    }

    public MemberDto memberupdate(int memberId, MemberDto memberDto) {
        Member updatedMember = mainRepository.findById(memberId).get();
        updatedMember = Member.builder().name(memberDto.getName()).email(memberDto.getEmail()).build();
        mainRepository.save(updatedMember);
        return convertMemberDto(updatedMember);
    }

    public MemberDto convertMemberDto(Member member) {
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();

        return memberDto;
    }

    public List<MemberDto> getAllMembers() {
        System.out.println("getAllMembers: ");
        List<Member> list = mainRepository.findAll();
        System.out.println("list: " + list);

        List<MemberDto> memberDtoList = list.stream().map(m -> convertMemberDto(m)).collect(Collectors.toList());
        memberDtoList.forEach(f -> System.out.println("f: " + f.getName()));
        return memberDtoList;
    }

}