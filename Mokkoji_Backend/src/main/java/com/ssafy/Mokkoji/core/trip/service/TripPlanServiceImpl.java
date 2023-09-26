package com.ssafy.Mokkoji.core.trip.service;

import com.ssafy.Mokkoji.core.attraction.domain.AttractionInfo;
import com.ssafy.Mokkoji.core.attraction.repository.AttractionInfoRepository;
import com.ssafy.Mokkoji.core.trip.domain.PlanAttraction;
import com.ssafy.Mokkoji.core.trip.domain.TripPlan;
import com.ssafy.Mokkoji.core.trip.domain.UserTripTeam;
import com.ssafy.Mokkoji.core.trip.domain.TeamRole;
import com.ssafy.Mokkoji.core.trip.repository.PlanAttractionRepository;
import com.ssafy.Mokkoji.core.trip.repository.TripPlanRepository;
import com.ssafy.Mokkoji.core.trip.repository.UserTripTeamRepository;
import com.ssafy.Mokkoji.core.trip.dto.request.TripPlanRequest;
import com.ssafy.Mokkoji.global.exception.NotFoundException;
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
public class TripPlanServiceImpl implements TripPlanService {

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
    @Transactional(readOnly = true)
    public TripPlan getTripPlan(Long tripPlanId, Long tripTeamId, Long userId) {

        userTripTeamRepository.getUserTripTeamByUserIdAndTeamId(userId, tripTeamId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 입력"));

        return tripPlanRepository.findTripPlanByIdJoinPlanAttraction(tripPlanId)
                .orElseThrow(() -> new NotFoundException("유효하지 않은 계획"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripPlan> getTripPlansByTripTeamId(Long tripTeamId) {
        return tripPlanRepository.findTripPlanListByTripTeamId(tripTeamId);
    }

    @Override
    public void deletePlanAttraction(Long userId, Long planAttractionId, Long tripTeamId) {
        getUserTripTeam(userId, tripTeamId);
        planAttractionRepository.deleteById(planAttractionId);
    }

    @Override
    public void editTripPlan(Long userId, Long tripPlanId, Long tripTeamId, TripPlanRequest tripPlanRequest) {
        getUserTripTeam(userId, tripTeamId);
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new NotFoundException("잘못된 입력입니다."));
        tripPlan.editPlan(tripPlanRequest.getPlanName(), tripPlanRequest.getPlanContent(), tripPlanRequest.getStartDate(), tripPlanRequest.getEndDate());
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
