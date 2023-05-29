package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.TripTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripTeamRepository extends JpaRepository<TripTeam, Long>, TripTeamRepositoryCustom {
}
