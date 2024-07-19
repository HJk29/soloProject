package com.springboot.board.repository;

import com.springboot.board.entity.Board;
import com.springboot.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByBoardId(long boardId);

    @Query("SELECT b FROM Board b WHERE b.questionStatus = 'QUESTION_REGISTERED' OR b.questionStatus = 'QUESTION_ANSWERED'")
    Page<Board> findByQuestionStatus(Pageable pageable);
}
