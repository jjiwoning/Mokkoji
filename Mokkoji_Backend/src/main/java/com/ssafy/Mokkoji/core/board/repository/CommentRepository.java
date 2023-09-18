package com.ssafy.Mokkoji.core.board.repository;

import com.ssafy.Mokkoji.core.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
