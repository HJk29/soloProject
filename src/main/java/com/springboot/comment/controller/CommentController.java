package com.springboot.comment.controller;

import com.springboot.comment.dto.CommentDto;
import com.springboot.comment.entity.Comment;
import com.springboot.comment.mapper.CommentMapper;
import com.springboot.comment.service.CommentService;
import com.springboot.dto.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v11/comments")
@Validated
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }


    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post commentPostDto){
        Comment comment = commentService.createComment(commentMapper.commentPostToComment(commentPostDto));

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") @Positive long commentId,
                                       @Valid @RequestBody CommentDto.Patch requestBody){
        requestBody.setCommentId(commentId);
        Comment comment = commentService.updateComment(commentMapper.commentPatchDtoToComment(requestBody));
        return new ResponseEntity(new SingleResponseDto<>(commentMapper.commentToCommentResponseDto(comment)), HttpStatus.OK);
    }


    @GetMapping("/{comment-id}")
    public ResponseEntity getComment(@PathVariable("comment-id") @Positive long commentId){
        Comment comment = commentService.findComment(commentId);

        CommentDto.Response commentResponseDto = commentMapper.commentToCommentResponseDto(comment);
        commentResponseDto.setBoardId(comment.getBoard().getBoardId());
        return new ResponseEntity<>(
                new SingleResponseDto<>(commentResponseDto), HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(
            @PathVariable("comment-id") @Positive long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
