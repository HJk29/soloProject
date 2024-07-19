package com.springboot.board.mapper;

import com.springboot.board.dto.BoardDto;
import com.springboot.board.entity.Board;
import com.springboot.comment.entity.Comment;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    default Board boardPostDtoToBoard(BoardDto.Post boardPostDto){
        Board board = new Board();
        Member member = new Member();

        member.setMemberId(boardPostDto.getMemberId());
        board.setMember(member);
        board.setContent(boardPostDto.getContent());
        board.setTitle(boardPostDto.getTitle());
        board.setBoardStatus(boardPostDto.getBoardStatus());

        return board;
    }
    BoardDto.Response boardToBoardResponseDto(Board board);
    Board boardPatchDtoToBoard(BoardDto.Patch boardPatchDto);
    List<BoardDto.Response> boardsToBoardResponses(List<Board> boards);
}
