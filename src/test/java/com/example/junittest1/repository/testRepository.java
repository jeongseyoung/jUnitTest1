package com.example.junittest1.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.junittest1.entity.Member;

/*
 * @DataJpaTest 는 기본적으로 내장된 메모리 데이터베이스를 이용해 테스트를 실행.
 * 물리적인 MySQL 데이터베이스를 연결해서 테스트를 할려고 하여 발생한 문제였다. -> replace none으로 해결? -> replace = Replace.NONE
 * //@AutoConfigureTestDatabase(replace = Replace.NONE)
 * 내장메모리로 테스트하려면 @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) 
 * H2 -> 내장메모리 데이터베이스
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class testRepository {

    @Autowired
    private MainRepository mainRepository;

    @Test
    public void save_then_returnSavedThings() {
        // arrange
        Member member = Member.builder().id(10).name("aqq").email("aqq@aqq.com").build();

        // act
        Member savedMember = mainRepository.save(member);

        // assert
        Assertions.assertThat(savedMember).isNotNull();
        Assertions.assertThat(savedMember.getId()).isGreaterThan(0);
    }

    @Test
    public void getAll() {
        // arrange
        Member member1 = Member.builder().id(10).name("aqq").email("aqq@aqq.com").build();
        // Member member2 =
        // Member.builder().id(11).name("abcd").email("abcd@abcd.com").build();

        // act
        mainRepository.save(member1);
        // mainRepository.save(member2);

        ArrayList<Member> members = (ArrayList<Member>) mainRepository.findAll();
        // assert
        Assertions.assertThat(members).isNotNull();
        // Assertions.assertThat(members.size()).isGreaterThan(4);
        Assertions.assertThat(members.size()).isEqualTo(2);
    }

    @Test
    public void getOneMember() {
        // arrange

        // act
        Member member = mainRepository.findById(252).get();

        // aasert
        Assertions.assertThat(member).isNotNull();
        Assertions.assertThat(member.getName()).isEqualTo("abb");
    }

    @Test
    public void updateMember() {
        // arrange

        // act
        Member member = mainRepository.findById(252).get();
        member.setName("aff");
        member.setEmail("aff@aff.com");

        Member updatedMember = mainRepository.findById(252).get();

        // assert
        Assertions.assertThat(updatedMember.getName()).isEqualTo("aff");
        Assertions.assertThat(updatedMember.getEmail()).isEqualTo("aff@aff.com");
    }

    @Test
    public void deleteAllMember() {
        // arrange

        // act
        mainRepository.deleteAll();
        List<Member> members = mainRepository.findAll();

        // assert
        Assertions.assertThat(members).isEmpty();
    }

    @Test
    public void deleteOneMember() {
        // arrange
        // act
        Member member = mainRepository.findById(252).get();
        mainRepository.delete(member); // id: 252
        Optional<Member> findmember = mainRepository.findById(252);

        // assert
        Assertions.assertThat(findmember).isEmpty();
    }
}
