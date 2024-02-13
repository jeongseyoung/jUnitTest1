package com.example.junittest1.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.junittest1.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    // Optional<Board> findBytitle(String title);
    List<Board> findByMemberId(int memberId);
    // Optional<Board> findBywrtier(String wrtier);
}
