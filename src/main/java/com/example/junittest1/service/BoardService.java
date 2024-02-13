package com.example.junittest1.service;

import java.util.*;
import org.springframework.stereotype.Service;

import com.example.junittest1.dto.BoardDto;
import com.example.junittest1.entity.Board;
import com.example.junittest1.entity.Member;
import com.example.junittest1.repository.BoardRepository;
import com.example.junittest1.repository.MainRepository;
import com.example.junittest1.utils.CustomException;
import com.example.junittest1.utils.ErrorCode;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MainRepository mainRepository;
    private final MainService mainService;

    public BoardDto write(int memberId, BoardDto boardDto) {
        Board board = mapToBoard(boardDto);
        Member member = mainRepository.findById(memberId).get();
        board.setMember(member);
        Board savedBoard = boardRepository.save(board);

        BoardDto mapToBoardDto = mapToBoardDto(savedBoard);

        // List<Board> list = member.getBoardList();
        // list.forEach(f -> System.out.println(f.getTitle()));
        return mapToBoardDto;

    }

    public BoardDto readBoard(int boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> {
            throw new CustomException("글을 찾을 수 없습니다!!", ErrorCode.NOT_FOUND_ZZZ);
        });
        return mapToBoardDto(board);
    }

    public void deleteBoard(BoardDto boardDto) {
        boardRepository.deleteById(boardDto.getId());
        // return "DELETED!!!";
    }

    public BoardDto updateBoard(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId()).get();
        board = Board.builder().title(boardDto.getTitle()).content(boardDto.getContent()).build();
        Board updatedBoard = boardRepository.save(board);
        return mapToBoardDto(updatedBoard);
    }

    public List<Board> myWritingList(int memberId) {
        List<Board> getMyBoardList = boardRepository.findByMemberId(memberId);
        getMyBoardList.stream().map(m -> mapToBoardDto(m)).collect(Collectors.toList());
        return getMyBoardList;
    }

    BoardDto mapToBoardDto(Board board) {
        BoardDto boardDto = BoardDto.builder()
                .Id(board.getId())
                .writer(board.getWriter())
                .title(board.getTitle())
                .content(board.getContent()).build();
        return boardDto;
    }

    Board mapToBoard(BoardDto boardDto) {
        Board board = Board.builder()
                .Id(boardDto.getId())
                .writer(boardDto.getWriter())
                .title(boardDto.getTitle())
                .content(boardDto.getContent()).build();
        return board;
    }

    public String autoSave(Member member) {
        Board board1 = Board.builder()
                .writer(member.getName())
                .title("@#$@$")
                .content("abd*^&(*c")
                .member(member)
                .build();

        Board board2 = Board.builder()
                .writer(member.getName())
                .title("@@@@@")
                .content("abBSD##$#")
                .member(member)
                .build();

        Board board3 = Board.builder()
                .writer(member.getName())
                .title("adsfsdf")
                .content("abcccc")
                .member(member)
                .build();

        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);

        member.getBoardList().add(board1);
        member.getBoardList().add(board2);
        member.getBoardList().add(board3);
        System.out.println("lllllllliiist: " + member.getId());
        mainRepository.save(member);
        // pls();
        return "완료!";
    }

    public String pls(int memberId) {
        System.out.println("pls");
        String getName = mainRepository.findById(memberId).get().getName();
        System.out.println("getId: " + getName);
        return getName;
    }
}
