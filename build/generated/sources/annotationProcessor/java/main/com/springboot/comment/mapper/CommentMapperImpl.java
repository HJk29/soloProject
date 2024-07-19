package com.springboot.comment.mapper;

import com.springboot.comment.dto.CommentDto;
import com.springboot.comment.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-19T15:45:36+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.23 (Azul Systems, Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDto.Response commentToCommentResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        String title = null;
        String content = null;

        title = comment.getTitle();
        content = comment.getContent();

        long boardId = 0L;

        CommentDto.Response response = new CommentDto.Response( boardId, title, content );

        return response;
    }

    @Override
    public Comment commentPatchDtoToComment(CommentDto.Patch commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentId( commentDto.getCommentId() );
        comment.setTitle( commentDto.getTitle() );
        comment.setContent( commentDto.getContent() );

        return comment;
    }
}
