package com.springboot.board.service;
import com.springboot.board.entity.Board;
import com.springboot.board.repository.BoardRepository;
import com.springboot.comment.entity.Comment;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import com.springboot.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    private final MemberService memberService;



    public BoardService(BoardRepository boardRepository,  MemberService memberService) {
        this.boardRepository = boardRepository;
        this.memberService = memberService;
    }

    public Board createBoard(Board board){

        Member member = memberService.findVerifiedMember(board.getMember().getMemberId());
        board.setMember(member);

        return boardRepository.save(board);
    }


    public Board updateBoard(Board board){
        Board findBoard = findVerifiedBoard(board.getBoardId());

        if(findBoard.getQuestionStatus() == Board.QuestionStatus.QUESTION_REGISTERED) {
            Optional.ofNullable(board.getMember())
                    .ifPresent(member -> findBoard.setMember(member));
            Optional.ofNullable(board.getContent())
                    .ifPresent(content -> findBoard.setContent(content));
            Optional.ofNullable(board.getTitle())
                    .ifPresent(title -> findBoard.setTitle(title));
            Optional.ofNullable(board.getComment())
                    .ifPresent(comment -> findBoard.setComment(comment));
            Optional.ofNullable(board.getBoardStatus())
                    .ifPresent(boardStatus -> findBoard.setBoardStatus(boardStatus));
            Optional.ofNullable(board.getQuestionStatus())
                    .ifPresent(questionStatus -> findBoard.setQuestionStatus(questionStatus));
        }
        else {
            new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND);
        }
        return boardRepository.save(findBoard);
    }

    public void deleteBoard(long boardId) {
        Board findBoard = findVerifiedBoard(boardId);
        findBoard.setQuestionStatus(Board.QuestionStatus.QUESTION_DELETED);
        updateBoard(findBoard);
    }

    public Board findBoard(long boardId){
        Board board = findVerifiedBoard(boardId);

        long getCount = board.getViewCount() + 1;
        board.setViewCount(getCount);
        boardRepository.save(board);

        return findVerifiedBoard(boardId);
    }

    public Page<Board> findBoards(int page, int size){
        PageRequest pageRequest = PageRequest.of(page -1, size, Sort.by("boardId").descending());
        return boardRepository.findByQuestionStatus(pageRequest);
    }


    public Board findVerifiedBoard(long boardId){
        Optional<Board> optionalBoard = boardRepository.findByBoardId(boardId);
        Board board = optionalBoard.orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
        return board;
    }


}
