package com.springboot.comment.service;

import com.springboot.board.entity.Board;
import com.springboot.board.service.BoardService;
import com.springboot.comment.dto.CommentDto;
import com.springboot.comment.entity.Comment;
import com.springboot.comment.repository.CommentRepository;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    public CommentService(CommentRepository commentRepository, MemberService memberService, BoardService boardService) {
        this.commentRepository = commentRepository;
        this.memberService = memberService;
        this.boardService = boardService;
    }

    public Comment createComment(Comment comment){
        Board board = boardService.findVerifiedBoard(comment.getBoard().getBoardId());
        memberService.findVerifiedMember(comment.getMember().getMemberId());

        board.setQuestionStatus(Board.QuestionStatus.QUESTION_ANSWERED);
        boardService.updateBoard(board);

        return commentRepository.save(comment);
    }

    public Comment findComment(long commentId){

        return findVerifiedComment(commentId);
    }

    public Comment updateComment(Comment comment){


        Comment findComment = findVerifiedComment(comment.getCommentId());
        Optional.ofNullable(comment.getMember())
                .ifPresent(member -> findComment.setMember(member));
        Optional.ofNullable(comment.getBoard())
                .ifPresent(board -> findComment.setBoard(board));
        Optional.ofNullable(comment.getTitle())
                .ifPresent(title -> findComment.setTitle(title));
        Optional.ofNullable(comment.getContent())
                .ifPresent(content -> findComment.setContent(content));

        return commentRepository.save(findComment);

    }
    public void deleteComment(long commentId) {
        Comment findComment = findVerifiedComment(commentId);
        commentRepository.delete(findComment);
    }

    public Comment findVerifiedComment(long commentId){
        Optional<Comment> optionalComment = commentRepository.findByCommentId(commentId);
        Comment comment = optionalComment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        return comment;
    }
}
