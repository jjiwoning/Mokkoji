package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.TripTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripTeamRepository extends JpaRepository<TripTeam, Long>, TripTeamRepositoryCustom {
}
