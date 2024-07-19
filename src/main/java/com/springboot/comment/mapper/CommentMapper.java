package com.springboot.comment.mapper;

import com.springboot.board.entity.Board;
import com.springboot.comment.dto.CommentDto;
import com.springboot.comment.entity.Comment;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment commentPostToComment(CommentDto.Post postComment){
        Comment comment = new Comment();
        Board board = new Board();
        Member member = new Member();

        board.setBoardId(postComment.getBoardId());
        member.setMemberId(postComment.getMemberId());
        comment.setContent(postComment.getContent());
        comment.setTitle(postComment.getTitle());
        comment.setMember(member);
        comment.setBoard(board);

        return comment;
    }
    CommentDto.Response commentToCommentResponseDto(Comment comment);
    Comment commentPatchDtoToComment(CommentDto.Patch commentDto);
}
