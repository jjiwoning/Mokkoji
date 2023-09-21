package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.TeamComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamCommentRepository extends JpaRepository<TeamComment, Long>, TeamCommentRepositoryCustom {
}
