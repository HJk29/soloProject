package com.springboot.board.mapper;

import com.springboot.board.dto.BoardDto;
import com.springboot.board.entity.Board;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-19T15:45:36+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.23 (Azul Systems, Inc.)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public BoardDto.Response boardToBoardResponseDto(Board board) {
        if ( board == null ) {
            return null;
        }

        long boardId = 0L;
        String title = null;
        String content = null;
        Board.QuestionStatus questionStatus = null;
        Board.BoardStatus boardStatus = null;

        if ( board.getBoardId() != null ) {
            boardId = board.getBoardId();
        }
        title = board.getTitle();
        content = board.getContent();
        questionStatus = board.getQuestionStatus();
        boardStatus = board.getBoardStatus();

        long memberId = 0L;
        String answer = null;
        long getCount = 0L;

        BoardDto.Response response = new BoardDto.Response( memberId, boardId, title, content, questionStatus, boardStatus, answer, getCount );

        return response;
    }

    @Override
    public Board boardPatchDtoToBoard(BoardDto.Patch boardPatchDto) {
        if ( boardPatchDto == null ) {
            return null;
        }

        Board board = new Board();

        board.setBoardId( boardPatchDto.getBoardId() );
        board.setTitle( boardPatchDto.getTitle() );
        board.setContent( boardPatchDto.getContent() );
        board.setBoardStatus( boardPatchDto.getBoardStatus() );

        return board;
    }

    @Override
    public List<BoardDto.Response> boardsToBoardResponses(List<Board> boards) {
        if ( boards == null ) {
            return null;
        }

        List<BoardDto.Response> list = new ArrayList<BoardDto.Response>( boards.size() );
        for ( Board board : boards ) {
            list.add( boardToBoardResponseDto( board ) );
        }

        return list;
    }
}
