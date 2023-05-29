package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.TeamBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamBoardRepository extends JpaRepository<TeamBoard, Long>, TeamBoardRepositoryCustom {
}
