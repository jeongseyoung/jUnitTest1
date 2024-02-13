package com.example.junittest1.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.junittest1.dto.BoardDto;
import com.example.junittest1.dto.MemberDto;
import com.example.junittest1.entity.Board;
import com.example.junittest1.entity.Member;
import com.example.junittest1.repository.BoardRepository;
import com.example.junittest1.repository.MainRepository;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;
    @Mock
    private MainRepository mainRepository;
    @InjectMocks
    private BoardService boardService;
    @InjectMocks
    private MainService mainService;

    private Board board;
    private Board board2;
    private Board board3;
    private BoardDto boardDto;
    private Member member;
    private MemberDto memberDto;

    @BeforeEach
    public void init() {
        member = Member.builder().name("aqq").email("aqq@aqq.com").build();
        memberDto = MemberDto.builder().name("bbb").email("bbb@bbb.com").build();
        board = Board.builder().writer("abb").title("HELLO~").content("what?").member(member).build();
        board2 = Board.builder().writer("bb").title("HELLO~bb").content("what?bb").member(member).build();
        board3 = Board.builder().writer("cc").title("HELLO~cc").content("what?cc").member(member).build();

        boardDto = BoardDto.builder().writer("addff").title("adffff").content("abcdfge").build();
    }

    @Test
    public void write_returnBoardDto() {

        when(mainRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(member)); // 찾아지는지?
        when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board); // 저장이되는지?

        BoardDto writeBoardDto = boardService.write(Mockito.anyInt(), boardDto);
        Assertions.assertThat(writeBoardDto).isNotNull(); // 저정하고 리턴이 제대로 되는지?
    }

    @Test // save - find - assertions
    public void find_success_returnBoardDto() {
        // when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
        when(boardRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(board));
        BoardDto getBoardDto = boardService.readBoard(Mockito.anyInt());
        Assertions.assertThat(getBoardDto).isNotNull();
    }

    @Test // 다시! -> error로 그린받아야됨
    public void find_failed_throwCustomException() {
        // when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
        Assertions.assertThat(boardService.readBoard(Mockito.anyInt()));
    }

    @Test
    public void myWritingList_returnBoardDtoList() {
        when(boardRepository.findByMemberId(Mockito.anyInt())).thenReturn(Arrays.asList(board));
        List<Board> getMyListDto = boardService.myWritingList(Mockito.anyInt());
        getMyListDto.forEach(f -> System.out.println("getMyListDto(Test): " + f.getTitle()));
        Assertions.assertThat(getMyListDto).isNotEmpty();
    }

    @Test
    public void delete_Success_returnVoid() {
        // when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
        // return void -> assertAll(() -> )
        assertAll(() -> boardService.deleteBoard(boardDto));

    }

    @Test // save - find - update - assertions
    public void update_returnUpdatedMemberDto() {
        // when save - find
        when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);
        when(boardRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(board));

        // update하고
        BoardDto updatedboardDto = boardService.updateBoard(boardDto);
        // System.out.println("updatedboardDto(@Test): " + updatedboardDto);

        // 리턴된 boarddto비었는지 확인 -> assertions
        Assertions.assertThat(updatedboardDto).isNotNull();
    }
}
