package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.TeamBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamBoardRepository extends JpaRepository<TeamBoard, Long>, TeamBoardRepositoryCustom {
}
