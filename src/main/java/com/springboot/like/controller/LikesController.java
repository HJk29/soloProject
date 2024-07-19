package com.springboot.like.controller;

import com.springboot.board.service.BoardService;
import com.springboot.like.dto.LikesDto;
import com.springboot.like.entity.Likes;
import com.springboot.like.mapper.LikesMapper;
import com.springboot.like.repository.LikesRepository;
import com.springboot.like.service.LikesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v11/likes")
@Validated
public class LikesController {
        private final LikesService likesService;
        private final LikesMapper likesMapper;

    public LikesController(LikesService likesService, LikesMapper likesMapper) {
        this.likesService = likesService;
        this.likesMapper = likesMapper;
    }


    @PostMapping
    public ResponseEntity postLikes(@Valid @RequestBody LikesDto.Post requestBody){
        Likes likes = likesService.createLikes(likesMapper.likesPostDtoToLikes(requestBody));

        return new ResponseEntity(HttpStatus.OK);
    }
}
