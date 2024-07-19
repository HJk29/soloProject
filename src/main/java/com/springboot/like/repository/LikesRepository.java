
package com.springboot.like.repository;


import com.springboot.board.entity.Board;
import com.springboot.like.entity.Likes;
import com.springboot.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByLikesId(long likesId);

    Optional<Likes> findByBoardBoardIdAndMemberMemberId(long boardId, long memberId);
}