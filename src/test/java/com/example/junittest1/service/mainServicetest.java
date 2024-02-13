package com.example.junittest1.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.junittest1.dto.MemberDto;
import com.example.junittest1.entity.Member;
import com.example.junittest1.repository.MainRepository;

/*
 * We do not want to hit DB.
 */
@ExtendWith(MockitoExtension.class)
public class mainServicetest {

    @Mock // do not want it to be touching DB. it's going to inject the actual mock.
    // and we don't have to do any constructors.
    private MainRepository mainRepository;

    @InjectMocks // it will bring in service.
    private MainService mainService;

    /*
     * CREATE, ADD
     */
    @Test
    public void createMember_returnMemberDto() {
        Member member = Member.builder().name("aqq").email("aqq@aqq.com").build();
        MemberDto memberDto = MemberDto.builder().name(member.getName()).email(member.getEmail()).build();

        when(mainRepository.save(Mockito.any(Member.class))).thenReturn(member);

        MemberDto savedMember = mainService.addMember(memberDto); // addmember에서 dto 리턴함

        Assertions.assertThat(savedMember).isNotNull();
    }

    /*
     * response test (pageable, page)
     * https://www.youtube.com/watch?v=4l3EFprMqpU
     */
    @Test
    public void getAllMembers() {

    }

    /*
     * GETONEMEMBER
     */
    @Test
    public void getOneMember_returnOneMemberDto() {
        // Member member = Member.builder().name("aqq").email("aqq@aqq.com").build();
        Member member2 = Member.builder().name("aaa").email("aaa@aaa.com").build();
        // MemberDto dto = MemberDto.builder().name("bbb").email("bbb@bbb.com").build();
        // when은 조건설정이라고 생각하면 됨???
        when(mainRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(member2));

        MemberDto memberDto = mainService.getOneMember(Mockito.anyInt());
        System.out.println("memberDto name: " + memberDto.getName());

        // 결과 확인??????
        Assertions.assertThat(memberDto).isNotNull();

        // print();
    }

    /*
     * UPDATE
     */
    @Test
    public void updateMember_returnMemberDto() {
        Member member = Member.builder().name("aqq").email("aqq@aqq.com").build();
        MemberDto memberDto = MemberDto.builder().name("bbb").email("bbb@bbb.com").build();

        when(mainRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(member));// 이러면 저 위에 만든 member를
        // 사용할 수 있음~?!
        // when();// save는 왜 해야됨??
        when(mainRepository.save(Mockito.any(Member.class))).thenReturn(member);
        MemberDto updated_MemberDto = mainService.memberupdate(Mockito.anyInt(), memberDto);
        System.out.println("updated_MemberDto: " + updated_MemberDto.getName());
        /*
         * what should i test?
         * public MemberDto memberupdate(int currentId, MemberDto memberDto) -> 업데이트하면
         * memberdto리턴이니깐 리턴받을 dto객체 하나만들고~
         * currentId와 Dto가 필요함.
         * currentId -> when(findById) thenresult(Optional.isnullable(member)) findById는
         * Optional 리턴
         * Dto -> service.addmember 에서 repository.save하면 member 리턴 -> Dto반환로 변환해서 리턴. ->
         * 그냥 Dto객체 하나 만들면 안됨?
         */
        Assertions.assertThat(updated_MemberDto).isNotNull();
    }

    /*
     * DELETE
     */
    @Test
    public void deleteMember() {
        Member member = Member.builder().name("aqq").email("aqq@aqq.com").build();

        // UnnecessaryStubbingException -> 필요없는 STUB
        // when(mainRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(member));

        assertAll(() -> mainService.deleteMember(Mockito.anyInt()));
    }
}
