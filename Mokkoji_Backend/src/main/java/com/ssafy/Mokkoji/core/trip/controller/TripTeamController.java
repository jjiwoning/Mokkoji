package com.ssafy.Mokkoji.core.trip.controller;

import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionAddRequest;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.core.trip.domain.TeamComment;
import com.ssafy.Mokkoji.core.trip.domain.TripPlan;
import com.ssafy.Mokkoji.core.trip.domain.TripTeam;
import com.ssafy.Mokkoji.core.trip.domain.UserTripTeam;
import com.ssafy.Mokkoji.core.trip.dto.request.*;
import com.ssafy.Mokkoji.core.trip.dto.response.*;
import com.ssafy.Mokkoji.core.trip.service.TeamBoardService;
import com.ssafy.Mokkoji.core.trip.service.TeamCommentService;
import com.ssafy.Mokkoji.core.trip.service.TripPlanService;
import com.ssafy.Mokkoji.core.trip.service.TripTeamService;
import com.ssafy.Mokkoji.global.auth.LoginTokenInfo;
import com.ssafy.Mokkoji.global.auth.annotation.Authenticated;
import com.ssafy.Mokkoji.global.auth.annotation.LoginRequired;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/TripTeam")
@RequiredArgsConstructor
public class TripTeamController {

    private final TripTeamService tripTeamService;

    private final TripPlanService tripPlanService;

    private final TeamBoardService teamBoardService;

    private final TeamCommentService teamCommentService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public String addTripTeam(
            @RequestBody @Valid final TripTeamAddRequest tripTeamAddRequest,
            @Authenticated final LoginTokenInfo user
    ) {
        tripTeamService.makeTripTeam(user.getUserId(), tripTeamAddRequest.getTeamName());

        return "생성이 완료되었습니다";
    }

    @GetMapping("/invite")
    @LoginRequired
    @ResponseStatus(HttpStatus.OK)
    public List<UserTripTeamForm> allInviteInfo(@Authenticated final LoginTokenInfo user) {
        List<UserTripTeam> allUserTripTeam = tripTeamService.getAllUserTripTeam(user.getUserId());

        return allUserTripTeam.stream()
                .map(UserTripTeamForm::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/invite")
    @ResponseStatus(HttpStatus.OK)
    public String inviteUser(
            @RequestBody @Valid final UserInviteRequest userInviteRequest,
            @Authenticated final LoginTokenInfo user
    ) {
        tripTeamService.inviteUser(user.getUserId(), userInviteRequest);

        return "생성이 완료되었습니다";
    }

    @GetMapping("/{tripTeamId}")
    @ResponseStatus(HttpStatus.OK)
    public TripTeamResponse getTripTeamInfo(@PathVariable final Long tripTeamId) {
        TripTeam tripTeam = tripTeamService.findTripTeam(tripTeamId);
        return new TripTeamResponse(tripTeam);
    }

    @PatchMapping("/{tripTeamId}")
    @ResponseStatus(HttpStatus.OK)
    public String editTripTeamInfo(
            @PathVariable final Long tripTeamId,
            @RequestBody @Valid final TripTeamUpdateRequest request,
            @Authenticated final LoginTokenInfo user
    ) {
        tripTeamService.editTripTeam(user.getUserId(), tripTeamId, request);
        return "수정이 완료되었습니다.";
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/plans")
    @ResponseStatus(HttpStatus.OK)
    public List<TripPlanListResponse> getTripPlansOfTripTeam(@PathVariable final Long tripTeamId) {
        return tripPlanService.getTripPlansByTripTeamId(tripTeamId).stream()
                .map(TripPlanListResponse::new)
                .collect(Collectors.toList());
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/boards")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamBoardListResponse> getTeamBoardOfTripTeam(
            @PathVariable final Long tripTeamId,
            @Valid final BoardSearch boardSearch
    ) {
        return teamBoardService.getAllTeamBoards(boardSearch, tripTeamId);
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/boards/{teamBoardId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamBoardDetailResponse getTeamBoardDetailOfTripTeam(@PathVariable final Long teamBoardId) {
        return teamBoardService.getTeamBoardDetail(teamBoardId);
    }

    @GetMapping("/{tripTeamId}/boards/{teamBoardId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamCommentResponse> getComments(@PathVariable final Long teamBoardId) {
        return teamCommentService.getAllTeamComment(teamBoardId);
    }

    @PostMapping("/{tripTeamId}/boards/{teamBoardId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public String addTeamComment(
            @PathVariable final Long teamBoardId,
            @RequestBody final TeamComment teamComment,
            @Authenticated final LoginTokenInfo user
    ) {
        teamCommentService.addTeamComment(teamComment.getContent(), teamBoardId, user.getUserId());
        return "작성이 완료되었습니다.";
    }

    @PatchMapping("/{tripTeamId}/boards/{teamBoardId}/comments/{teamCommentId}")
    @ResponseStatus(HttpStatus.OK)
    public String modifyComment(
            @PathVariable final Long teamCommentId,
            @RequestBody final TeamComment teamComment,
            @Authenticated final LoginTokenInfo user
    ) {
        teamCommentService.editTeamComment(teamCommentId, teamComment.getContent(), user.getUserId());
        return "수정이 완료되었습니다.";

    }

    @DeleteMapping("/{tripTeamId}/boards/{teamBoardId}/comments/{teamCommentId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTeamComment(
            @PathVariable final Long teamCommentId,
            @Authenticated final LoginTokenInfo user
    ) {
        teamCommentService.deleteTeamComment(teamCommentId, user.getUserId());
        return "삭제가 완료되었습니다.";
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/boards/{teamBoardId}/comments/{teamCommentId}/validWriter")
    @ResponseStatus(HttpStatus.OK)
    public boolean isCommentWriter(
            @PathVariable final Long teamCommentId,
            @Authenticated final LoginTokenInfo user
    ) {
        return teamCommentService.isTeamCommentWriter(user.getUserId(), teamCommentId);
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/boards/{teamBoardId}/validWriter")
    @ResponseStatus(HttpStatus.OK)
    public boolean isBoardWriter(
            @PathVariable final Long teamBoardId,
            @Authenticated final LoginTokenInfo user
    ) {
        return teamBoardService.isTeamBoardWriter(user.getUserId(), teamBoardId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{tripTeamId}/boards/write")
    public String registerTeamBoard(
            @PathVariable final Long tripTeamId,
            @RequestBody @Valid final TeamBoardAddRequest teamBoardAddRequest,
            @Authenticated final LoginTokenInfo user
    ) {
        teamBoardService.addTeamBoard(teamBoardAddRequest, tripTeamId, user.getUserId());
        return "생성이 완료되었습니다";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{tripTeamId}/boards/{teamBoardId}")
    public String modifyTeamBoard(
            @PathVariable final Long teamBoardId,
            @RequestBody @Valid final TeamBoardAddRequest teamBoardAddRequest,
            @Authenticated final LoginTokenInfo user
    ) {
        teamBoardService.updateTeamBoard(teamBoardId, teamBoardAddRequest.getTitle(), teamBoardAddRequest.getContent(), user.getUserId());
        return "수정이 완료되었습니다";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{tripTeamId}/boards/{teamBoardId}")
    public String deleteBoard(
            @PathVariable final Long teamBoardId,
            @Authenticated final LoginTokenInfo user
    ) {
        teamBoardService.deleteTeamBoard(teamBoardId, user.getUserId());
        return "삭제가 완료되었습니다";
    }


    @LoginRequired
    @GetMapping("/{tripTeamId}/invite/{userTripTeamId}")
    @ResponseStatus(HttpStatus.OK)
    public UserTripTeamForm getUserTripTeamForm(
            @PathVariable final Long tripTeamId,
            @PathVariable final Long userTripTeamId
    ) {
        UserTripTeam userTripTeam = tripTeamService.getUserTripTeam(userTripTeamId);
        return new UserTripTeamForm(userTripTeam);
    }

    @PostMapping("/{tripTeamId}/invite/{userTripTeamId}/accept")
    @ResponseStatus(HttpStatus.OK)
    public String acceptInvite(
            @PathVariable final Long tripTeamId,
            @PathVariable final Long userTripTeamId,
            @Authenticated final LoginTokenInfo user
    ) {
        tripTeamService.acceptInvite(userTripTeamId, user.getUserId(), tripTeamId);
        return "초대를 수락했습니다";
    }

    @PostMapping("/{tripTeamId}/invite/{userTripTeamId}/refuse")
    @ResponseStatus(HttpStatus.OK)
    public String refuseInvite(
            @PathVariable final Long tripTeamId,
            @PathVariable final Long userTripTeamId,
            @Authenticated final LoginTokenInfo user
    ) {
        tripTeamService.refuseInvite(userTripTeamId, user.getUserId(), tripTeamId);
        return "초대를 거절했습니다";
    }

    @DeleteMapping("/{tripTeamId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTeam(
            @PathVariable final Long tripTeamId,
            @Authenticated final LoginTokenInfo user
    ) {
        tripTeamService.deleteTripTeam(tripTeamId, user.getUserId());
        return "팀 해체가 완료되었습니다.";
    }

    @PostMapping("/{tripTeamId}/addTripPlan")
    @ResponseStatus(HttpStatus.OK)
    public String addTripPlan(
            @PathVariable final Long tripTeamId,
            @RequestBody @Valid final TripPlanRequest tripPlanRequest,
            @Authenticated final LoginTokenInfo user
    ) {
        log.info("tripPlanRequestDto.getStartDate() = {}", tripPlanRequest.getStartDate());
        tripPlanService.makeTripPlan(
                user.getUserId(),
                tripTeamId,
                tripPlanRequest.getPlanName(),
                tripPlanRequest.getPlanContent(),
                tripPlanRequest.getStartDate(),
                tripPlanRequest.getEndDate());
        return "계획 생성이 완료되었습니다.";
    }

    @PostMapping("/{tripTeamId}/{tripPlanId}/addAttraction")
    @ResponseStatus(HttpStatus.OK)
    public void addAttraction(
            @PathVariable final Long tripTeamId,
            @PathVariable final Long tripPlanId,
            @RequestBody @Valid final List<AttractionAddRequest> attractionInfo,
            @Authenticated final LoginTokenInfo user
    ) {
        List<Integer> attractionIdList = attractionInfo.stream().map(AttractionAddRequest::getContentId).collect(Collectors.toList());
        tripPlanService.addPlanAttractions(user.getUserId(), tripTeamId, tripPlanId, attractionIdList);
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/{tripPlanId}")
    @ResponseStatus(HttpStatus.OK)
    public TripPlanResponse getTripPlan(
            @PathVariable final Long tripTeamId,
            @PathVariable final Long tripPlanId,
            @Authenticated final LoginTokenInfo user
    ) {
        TripPlan tripPlan = tripPlanService.getTripPlan(tripPlanId, tripTeamId, user.getUserId());
        return new TripPlanResponse(tripPlan);
    }

    @LoginRequired
    @PatchMapping("/{tripTeamId}/{tripPlanId}")
    @ResponseStatus(HttpStatus.OK)
    public String editTripPlan(
            @PathVariable final Long tripTeamId,
            @PathVariable final Long tripPlanId,
            @RequestBody @Valid final TripPlanRequest tripPlanRequest,
            @Authenticated final LoginTokenInfo user
    ) {
        tripPlanService.editTripPlan(user.getUserId(), tripPlanId, tripTeamId, tripPlanRequest);
        return "수정이 완료되었습니다.";
    }

    @DeleteMapping("/{tripTeamId}/{tripPlanId}/{planAttractionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAttraction(
            @PathVariable final Long planAttractionId,
            @PathVariable final Long tripTeamId,
            @Authenticated final LoginTokenInfo user
    ) {
        tripPlanService.deletePlanAttraction(user.getUserId(), planAttractionId, tripTeamId);
    }

    @DeleteMapping("/{tripTeamId}/{tripPlanId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTripPlan(
            @PathVariable final Long tripTeamId,
            @PathVariable final Long tripPlanId,
            @Authenticated final LoginTokenInfo user
    ) {
        tripPlanService.deleteTripPlan(user.getUserId(), tripPlanId, tripTeamId);
    }

    @GetMapping("/{tripTeamId}/user/leader")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public boolean validLeader(
            @PathVariable final Long tripTeamId,
            @Authenticated final LoginTokenInfo user
    ) {
        return tripTeamService.validUserIsLeader(user.getUserId(), tripTeamId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<TripTeamListResponse> getAllTripTeam(@Authenticated final LoginTokenInfo user) {
        return tripTeamService.getAllTripTeamByUserId(user.getUserId()).stream()
                .map(TripTeamListResponse::new)
                .collect(Collectors.toList());
    }
}
