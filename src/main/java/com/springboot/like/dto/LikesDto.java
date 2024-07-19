package com.springboot.like.dto;

import com.springboot.like.entity.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class LikesDto {

    @Getter
    @NoArgsConstructor
    public static class Post{
        private long memberId;
        private long boardId;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response{
        private long memberId;
        private long boardId;
//        private Likes.LikesStatus likesStatus;
    }
}
