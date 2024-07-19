package com.springboot.like.mapper;

import com.springboot.like.dto.LikesDto;
import com.springboot.like.entity.Likes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-19T15:45:36+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.23 (Azul Systems, Inc.)"
)
@Component
public class LikesMapperImpl implements LikesMapper {

    @Override
    public Likes likesToLikesResponseDto(Likes likes) {
        if ( likes == null ) {
            return null;
        }

        Likes likes1 = new Likes();

        likes1.setLikesId( likes.getLikesId() );
        likes1.setBoard( likes.getBoard() );
        likes1.setMember( likes.getMember() );

        return likes1;
    }

    @Override
    public Likes likesResponseDtoToLikes(LikesDto.Response likesDto) {
        if ( likesDto == null ) {
            return null;
        }

        Likes likes = new Likes();

        return likes;
    }
}
