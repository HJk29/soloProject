package com.springboot.like.mapper;

import com.springboot.board.entity.Board;
import com.springboot.like.dto.LikesDto;
import com.springboot.like.entity.Likes;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LikesMapper {
    default Likes likesPostDtoToLikes(LikesDto.Post likesPostDto){
        Likes likes = new Likes();
        Board board = new Board();
        Member member = new Member();

        board.setBoardId(likesPostDto.getBoardId());
        member.setMemberId(likesPostDto.getMemberId());
        likes.setBoard(board);
        likes.setMember(member);

        return likes;
    }
    Likes likesToLikesResponseDto(Likes likes);
    Likes likesResponseDtoToLikes(LikesDto.Response likesDto);
}
