package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.*;
import com.ssafy.enjoytrip.domain.team_relation.TeamRole;
import com.ssafy.enjoytrip.dto.request.TripPlanRequestDto;
import com.ssafy.enjoytrip.exception.NotFoundException;
import com.ssafy.enjoytrip.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TripPlanServiceImpl implements TripPlanService{

    private final AttractionInfoRepository attractionInfoRepository;
    private final TripPlanRepository tripPlanRepository;
    private final UserTripTeamRepository userTripTeamRepository;
    private final PlanAttractionRepository planAttractionRepository;

    @Override
    public void makeTripPlan(Long userId, Long tripTeamId, String planName, String planContent, LocalDate startDate, LocalDate endDate) {

        UserTripTeam userTripTeam = getUserTripTeam(userId, tripTeamId);

        TripPlan tripPlan = TripPlan.builder()
                .planContent(planContent)
                .planName(planName)
                .tripTeam(userTripTeam.getTripTeam())
                .startDate(startDate)
                .endDate(endDate)
                .build();
        tripPlanRepository.save(tripPlan);
    }

    @Override
    public void addPlanAttractions(Long userId, Long tripTeamId, Long tripPlanId, List<Integer> attractionIdList) {

        UserTripTeam userTripTeam = getUserTripTeam(userId, tripTeamId);

        TripPlan tripPlan = tripPlanRepository.findTripPlanByIdJoinTripTeam(tripPlanId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 팀"));

        if (tripPlan.getTripTeam() != userTripTeam.getTripTeam()) {
            throw new IllegalArgumentException("해당 팀의 계획이 아닙니다");
        }

        long order = tripPlanRepository.findMaxOrder(tripPlanId)
                .orElse(0L);

        log.info("order = {}", order);

        for(Integer attractionId: attractionIdList){
            AttractionInfo attractionInfo = attractionInfoRepository.findById(attractionId)
                    .orElseThrow(() -> new NotFoundException("유효하지 않은 관광지"));
            PlanAttraction planAttraction = PlanAttraction.builder().attractionInfo(attractionInfo).planOrder(++order).build();
            tripPlan.addPlanAttraction(planAttraction);
        }
    }

    @Override
    public void deleteTripPlan(Long userId, Long tripPlanId, Long tripTeamId) {
        getUserTripTeam(userId, tripTeamId);
        planAttractionRepository.deletePlanAttractionsByTripPlanId(tripPlanId);
        tripPlanRepository.deleteById(tripPlanId);
    }

    @Override
    public TripPlan getTripPlan(Long tripPlanId, Long tripTeamId, Long userId) {

        userTripTeamRepository.getUserTripTeamByUserIdAndTeamId(userId, tripTeamId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 입력"));

        return tripPlanRepository.findTripPlanByIdJoinPlanAttraction(tripPlanId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 계획"));
    }

    @Override
    public List<TripPlan> getTripPlansByTripTeamId(Long tripTeamId) {
        return tripPlanRepository.findTripPlanListByTripTeamId(tripTeamId);
    }

    @Override
    public void deletePlanAttraction(Long userId, Long planAttractionId, Long tripTeamId) {
        getUserTripTeam(userId, tripTeamId);
        planAttractionRepository.deleteById(planAttractionId);
    }

    @Override
    public void editTripPlan(Long userId, Long tripPlanId, Long tripTeamId, TripPlanRequestDto tripPlanRequestDto) {
        getUserTripTeam(userId, tripTeamId);
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new NotFoundException("잘못된 입력입니다."));
        tripPlan.editPlan(tripPlanRequestDto.getPlanName(), tripPlanRequestDto.getPlanContent(), tripPlanRequestDto.getStartDate(), tripPlanRequestDto.getEndDate());
    }
    private UserTripTeam getUserTripTeam(Long userId, Long tripTeamId) {
        UserTripTeam userTripTeam = userTripTeamRepository.getUserTripTeamByUserIdAndTeamId(userId, tripTeamId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 입력"));

        // 유저 권한 체크
        if (userTripTeam.getTeamRole() != TeamRole.LEADER) {
            throw new IllegalArgumentException("유효하지 않은 입력");
        }
        return userTripTeam;
    }

}
