package com.springboot.board.controller;


import com.springboot.board.dto.BoardDto;
import com.springboot.board.entity.Board;
import com.springboot.board.mapper.BoardMapper;
import com.springboot.board.repository.BoardRepository;
import com.springboot.board.service.BoardService;
import com.springboot.dto.MultiResponseDto;
import com.springboot.dto.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v11/boards")
@Validated
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    public BoardController(BoardService boardService, BoardMapper boardMapper, BoardRepository boardRepository) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.boardRepository = boardRepository;
    }


    @PostMapping
    public ResponseEntity postBoard(@Valid @RequestBody BoardDto.Post boardPostDto){

        Board board = boardService.createBoard(boardMapper.boardPostDtoToBoard(boardPostDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{board-id}")
    public ResponseEntity patchBoard(@PathVariable("board-id") @Positive long boardId,
                                     @Valid @RequestBody BoardDto.Patch requestBody){
        requestBody.setBoardId(boardId);
        Board board = boardService.updateBoard(boardMapper.boardPatchDtoToBoard(requestBody));

        return new ResponseEntity(new SingleResponseDto<>(boardMapper.boardToBoardResponseDto(board)), HttpStatus.OK);
    }

    @GetMapping("/{board-id}")
    public ResponseEntity getBoard( @PathVariable("board-id") @Positive long boardId){

        Board board = boardService.findBoard(boardId);

        BoardDto.Response boardResponseDto = boardMapper.boardToBoardResponseDto(board);
        boardResponseDto.setMemberId(board.getMember().getMemberId());

        return new ResponseEntity<>(new SingleResponseDto<>(boardResponseDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getBoards(@Positive @RequestParam int page,
                                    @Positive @RequestParam int size){
        Page<Board> pageBoards = boardService.findBoards(page -1, size);
        List<Board> boards = pageBoards.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(boardMapper.boardsToBoardResponses(boards), pageBoards), HttpStatus.OK);
    }

    @DeleteMapping("/{board-id}")
    public ResponseEntity deleteBoard(
            @PathVariable("board-id") @Positive long boardId) {
        boardService.deleteBoard(boardId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
