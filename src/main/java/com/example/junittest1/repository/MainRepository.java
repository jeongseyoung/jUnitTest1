package com.example.junittest1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.junittest1.entity.Member;

/**
 * MainRepository
 */
public interface MainRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByname(String name);

    void deleteByname(String name);

    // void updateMember(Member member);
}