package com.springboot.like.service;

import com.springboot.board.entity.Board;
import com.springboot.board.repository.BoardRepository;
import com.springboot.board.service.BoardService;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.like.entity.Likes;
import com.springboot.like.repository.LikesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final BoardRepository boardRepository;

    public LikesService(LikesRepository likesRepository, BoardRepository boardRepository) {
        this.likesRepository = likesRepository;
        this.boardRepository = boardRepository;
    }

    public Likes createLikes(Likes likes){

        Board board = boardRepository.findByBoardId(likes.getBoard().getBoardId()).get();

        System.out.println(likes.getBoard().getBoardId());
        Optional<Likes> findLikes =
                likesRepository.findByBoardBoardIdAndMemberMemberId(likes.getBoard().getBoardId(), likes.getMember().getMemberId());

        if(findLikes.isPresent()){ // 항상 비어있음.

            likesRepository.delete(findLikes.get());
            board.setLikesCount(board.getLikesCount() - 1);
            boardRepository.save(board);
            return null;
        }
        else {
            board.setLikesCount(board.getLikesCount() + 1);
            boardRepository.save(board);
            return likesRepository.save(likes);
        }
    }

}
