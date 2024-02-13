package com.example.junittest1.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.junittest1.dto.BoardDto;
import com.example.junittest1.entity.Board;
import com.example.junittest1.entity.Member;
import com.example.junittest1.repository.BoardRepository;
import com.example.junittest1.repository.MainRepository;
import com.example.junittest1.service.BoardService;
import com.example.junittest1.utils.CustomException;
import com.example.junittest1.utils.ErrorCode;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MainRepository mainRepository;
    private final BoardRepository boardRepository;

    // write
    // {
    // "writer":"abb",
    // "title":"helloidf",
    // "content":"abb@abb.com."
    // }
    @PostMapping("/board/write")
    public ResponseEntity<BoardDto> getMethodName(@RequestBody BoardDto boardDto) {
        int memberId = mainRepository.findByname(boardDto.getWriter()).get().getId();
        return new ResponseEntity<BoardDto>(boardService.write(memberId, boardDto), HttpStatus.OK);
    }

    @GetMapping("/board/read")
    public ResponseEntity<BoardDto> readBoard(int boardId) {
        return new ResponseEntity<BoardDto>(boardService.readBoard(boardId), HttpStatus.OK);
    }

    // delete
    @GetMapping("/board/delete")
    public ResponseEntity<?> deleteBoard(BoardDto boardDto) {
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }

    // update
    @PatchMapping("/board/update")
    public ResponseEntity<BoardDto> updateBoard(@RequestBody BoardDto boardDto) {
        return new ResponseEntity<BoardDto>(boardService.updateBoard(boardDto), HttpStatus.OK);
    }

    // myWritingList
    @PostMapping("/board/mylist")
    public ResponseEntity<List<Board>> myWritingList(@RequestBody Member member) {
        int getMemberId = mainRepository.findByname(member.getName()).get().getId();
        return new ResponseEntity<List<Board>>(boardService.myWritingList(getMemberId), HttpStatus.OK);
    }

    @PostMapping("/board/autosave")
    public ResponseEntity<String> autoSave(@RequestBody BoardDto boardDto) {
        Member member = mainRepository.findById(1).orElseThrow(() -> {
            throw new CustomException("not founddddddddd", ErrorCode.NOT_FOUND_ZZZ);
        });
        return ResponseEntity.ok().body(boardService.autoSave(member));

    }

}
