package com.example.junittest1.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "junitboardtest1")
public class Board {
    @Id
    @GeneratedValue
    private int Id;
    private String writer;
    private String title;
    private String content;
    // @CurrentTimestamp
    // private Date write_date;

    // manytoone에는 mapped by가 없음 -> 항상 주인이니깐?? 뭔솔?
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "member") // fk!!!!!
    private Member member;
}