package com.springboot.board.entity;

import com.springboot.board.Timestamped;
import com.springboot.comment.entity.Comment;
import com.springboot.like.entity.Likes;
import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.beans.Visibility;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private long viewCount = 0;

    @Column
    private long likesCount = 0;

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus = BoardStatus.BOARD_PUBLIC;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_REGISTERED;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(mappedBy = "board")
    private Comment comment;

    @OneToMany(mappedBy = "board")
    private List<Likes> likes = new ArrayList<>();



    public enum QuestionStatus {
        QUESTION_REGISTERED(1, "질문 등록"),
        QUESTION_ANSWERED(2, "답변 완료"),
        QUESTION_DELETED(3, "질문 삭제"),
        QUESTION_DEACTIVED(4, "질문 비활성화");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        QuestionStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

    public enum BoardStatus {
        BOARD_PUBLIC(1, "공개글"),
        BOARD_SECRET(2, "비공개글");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        BoardStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}
