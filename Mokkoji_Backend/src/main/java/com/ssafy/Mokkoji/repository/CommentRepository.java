package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
