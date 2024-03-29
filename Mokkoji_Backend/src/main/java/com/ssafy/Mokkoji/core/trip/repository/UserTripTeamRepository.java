package com.ssafy.Mokkoji.core.trip.repository;

import com.ssafy.Mokkoji.core.trip.domain.UserTripTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserTripTeamRepository extends JpaRepository<UserTripTeam, Long>, UserTripTeamRepositoryCustom {

    @Query("select utt from UserTripTeam utt where utt.userTripTeamId = :id and utt.user.userId = :userId and utt.tripTeam.tripTeamId = :tripTeamId")
    Optional<UserTripTeam> findUserTripTeamByIdAndUserAndTeam(@Param("id") Long id, @Param("userId") Long userId, @Param("tripTeamId") Long tripTeamId);

    @Query("select utt from UserTripTeam utt join fetch utt.tripTeam where utt.userTripTeamId = :id")
    Optional<UserTripTeam> findByUserTripTeamIdJoinTripTeam(@Param("id") Long id);
}
