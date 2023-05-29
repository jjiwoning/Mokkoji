package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.TeamComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamCommentRepository extends JpaRepository<TeamComment, Long>, TeamCommentRepositoryCustom {
}
