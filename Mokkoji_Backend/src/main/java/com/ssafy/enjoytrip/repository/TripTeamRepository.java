package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.TripTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripTeamRepository extends JpaRepository<TripTeam, Long>, TripTeamRepositoryCustom {
}
