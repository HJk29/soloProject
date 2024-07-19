package com.springboot.board.dto;

import com.springboot.board.entity.Board;
import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class  BoardDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotNull
        private long memberId;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        private Board.BoardStatus boardStatus;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private long boardId;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        private Board.BoardStatus boardStatus;

        public void setBoardId(long boardId) {this.boardId = boardId;}
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response {
        private long memberId;
        private long boardId;
        private String title;
        private String content;
        private Board.QuestionStatus questionStatus;
        private Board.BoardStatus boardStatus;
        private String answer;
        private long getCount;
    }
}