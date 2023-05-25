package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.TeamComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamCommentRepository extends JpaRepository<TeamComment, Long>, TeamCommentRepositoryCustom {
}
