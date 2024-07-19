package com.springboot.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_EXISTS(409, "MEMBER EXISTS"),
    BOARD_NOT_CHANGE(410, "BOARD_NOT_CHANGE"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "INVALID Member Status"),
    BOARD_NOT_FOUND(404, "Board Not Found"),
    COMMENT_NOT_FOUND(404, "Comment Not Found"),
    LIKE_NOT_FOUND(404, "Like Not Found");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}

