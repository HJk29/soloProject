package com.springboot.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotNull
        private long boardId;

        @NotNull
        private long memberId;

        @NotBlank
        private String title;

        @NotBlank
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @Setter
    public static class Patch {

        private long commentId;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        public void setCommentId(long commentId) {this.commentId = commentId;}
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response{
        private long boardId;
//        private long memberId;
        private String title;
        private String content;

    }

}
