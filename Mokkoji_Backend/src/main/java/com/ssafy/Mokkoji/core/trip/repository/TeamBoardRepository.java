package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.TeamBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamBoardRepository extends JpaRepository<TeamBoard, Long>, TeamBoardRepositoryCustom {
}
