package com.example.junittest1.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Member
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "junittest1")
public class Member {

    @Id
    @GeneratedValue // (strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    /*
     * 주인은 mappedBy 속성을 사용하지 않는다.
     * 주인이 아니면 mappedBy 속성을 사용해서 속성의 값으로 연관관계의 주인을 지정해야 한다
     */
    // member1이 여러 글을 쓸 수 있음.... -> member1 -> 주인
    // (board에서) -> ?어디에서해야됨? -> 어쨋든 mapped by 걸어 member가 주인임을 명시한다
    // @JoinColumn(name = "board_id")
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) // mapped by를 설정하지 않으면 중간 테이블이 자동으로 생성되기 때문에 비효율적임
    List<Board> boardList = new ArrayList<Board>();
}