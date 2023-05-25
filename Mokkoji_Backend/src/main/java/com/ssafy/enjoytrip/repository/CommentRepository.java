package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
