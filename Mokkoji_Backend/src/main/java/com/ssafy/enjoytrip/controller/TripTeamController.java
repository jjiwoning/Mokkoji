package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.domain.*;
import com.ssafy.enjoytrip.dto.request.*;
import com.ssafy.enjoytrip.dto.response.*;
import com.ssafy.enjoytrip.service.TeamBoardService;
import com.ssafy.enjoytrip.service.TeamCommentService;
import com.ssafy.enjoytrip.service.TripPlanService;
import com.ssafy.enjoytrip.service.TripTeamService;
import com.ssafy.enjoytrip.token.LoginRequired;
import com.ssafy.enjoytrip.token.LoginTokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ssafy.enjoytrip.token.LoginTokenConst.USER_INFO;

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
    public String addTripTeam(@RequestBody @Valid TripTeamAddRequestDto tripTeamAddRequestDto, HttpServletRequest request) {

        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);

        tripTeamService.makeTripTeam(user.getUserId(), tripTeamAddRequestDto.getTeamName());

        return "생성이 완료되었습니다";
    }

    @PostMapping("/invite")
    @ResponseStatus(HttpStatus.OK)
    public String inviteUser(@RequestBody @Valid UserInviteDto userInviteDto, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);

        log.info("userInviteDto.getUserId() = {}", userInviteDto.getUserId());
        log.info("userInviteDto.getTeamId() = {}", userInviteDto.getTeamId());

        tripTeamService.inviteUser(user.getUserId(), userInviteDto.getUserId(), userInviteDto.getTeamId());

        return "생성이 완료되었습니다";
    }

    @GetMapping("/{tripTeamId}")
    @ResponseStatus(HttpStatus.OK)
    public TripTeamResponseDto getTripTeamInfo(@PathVariable Long tripTeamId) {
        TripTeam tripTeam = tripTeamService.findTripTeam(tripTeamId);
        return new TripTeamResponseDto(tripTeam);
    }

    @PatchMapping("/{tripTeamId}")
    @ResponseStatus(HttpStatus.OK)
    public String editTripTeamInfo(@PathVariable Long tripTeamId, @RequestBody Map<String, String> map, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        tripTeamService.editTripTeam(user.getUserId(), tripTeamId, map.get("teamName"));
        return "수정이 완료되었습니다.";
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/plans")
    @ResponseStatus(HttpStatus.OK)
    public List<TripPlanListResponseDto> getTripPlansOfTripTeam(@PathVariable Long tripTeamId) {
        return tripPlanService.getTripPlansByTripTeamId(tripTeamId)
                .stream().map(TripPlanListResponseDto::new).collect(Collectors.toList());
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/boards")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamBoardListResponseDto> getTeamBoardOfTripTeam(@PathVariable Long tripTeamId, @Valid BoardSearch boardSearch) {
        return teamBoardService.getAllTeamBoards(boardSearch, tripTeamId)
                .stream().map(TeamBoardListResponseDto::new).collect(Collectors.toList());
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/boards/{teamBoardId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamBoardDetailResponseDto getTeamBoardDetailOfTripTeam(@PathVariable Long teamBoardId) {
        return new TeamBoardDetailResponseDto(teamBoardService.getTeamBoardDetail(teamBoardId));
    }

    @GetMapping("/{tripTeamId}/boards/{teamBoardId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamCommentResponseDto> getComments(@PathVariable long teamBoardId) {
        return teamCommentService.getAllTeamComment(teamBoardId).stream()
                .map(TeamCommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{tripTeamId}/boards/{teamBoardId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public String addTeamComment(@PathVariable long teamBoardId, @RequestBody TeamComment teamComment, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        teamCommentService.addTeamComment(teamComment.getContent(), teamBoardId, user.getUserId());
        return "작성이 완료되었습니다.";
    }

    @PatchMapping("/{tripTeamId}/boards/{teamBoardId}/comments/{teamCommentId}")
    @ResponseStatus(HttpStatus.OK)
    public String modifyComment(@PathVariable long teamCommentId,
                                              @RequestBody TeamComment teamComment,
                                              HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        teamCommentService.editTeamComment(teamCommentId, teamComment.getContent(), user.getUserId());
        return "수정이 완료되었습니다.";

    }

    @DeleteMapping("/{tripTeamId}/boards/{teamBoardId}/comments/{teamCommentId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTeamComment(@PathVariable long teamCommentId, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        teamCommentService.deleteTeamComment(teamCommentId, user.getUserId());
        return "삭제가 완료되었습니다.";
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/boards/{teamBoardId}/comments/{teamCommentId}/validWriter")
    @ResponseStatus(HttpStatus.OK)
    public boolean isCommentWriter(@PathVariable long teamCommentId, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        return teamCommentService.isTeamCommentWriter(user.getUserId(), teamCommentId);
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/boards/{teamBoardId}/validWriter")
    @ResponseStatus(HttpStatus.OK)
    public boolean isBoardWriter(@PathVariable long teamBoardId, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        return teamBoardService.isTeamBoardWriter(user.getUserId(), teamBoardId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{tripTeamId}/boards/write")
    public String registerTeamBoard(@PathVariable Long tripTeamId, @RequestBody TeamBoardAddRequestDto teamBoardAddRequestDto, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        TripTeam tripTeam = tripTeamService.findTripTeam(tripTeamId);

        TeamBoard board = TeamBoard.builder()
                .title(teamBoardAddRequestDto.getTitle())
                .content(teamBoardAddRequestDto.getContent())
                .tripTeam(tripTeam)
                .build();

        teamBoardService.addTeamBoard(board, user.getUserId());

        return "생성이 완료되었습니다";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{tripTeamId}/boards/{teamBoardId}")
    public String modifyTeamBoard(@PathVariable long teamBoardId, @RequestBody TeamBoardAddRequestDto teamBoardAddRequestDto, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        TeamBoard board = teamBoardService.getTeamBoardDetail(teamBoardId);
        if (!board.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        teamBoardService.updateTeamBoard(teamBoardId, teamBoardAddRequestDto.getTitle(), teamBoardAddRequestDto.getContent());
        return "수정이 완료되었습니다";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{tripTeamId}/boards/{teamBoardId}")
    public String deleteBoard(@PathVariable long teamBoardId, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        teamBoardService.deleteTeamBoard(teamBoardId, user.getUserId());
        return "삭제가 완료되었습니다";
    }


    @LoginRequired
    @GetMapping("/{tripTeamId}/invite/{userTripTeamId}")
    @ResponseStatus(HttpStatus.OK)
    public UserTripTeamForm getUserTripTeamForm(@PathVariable Long tripTeamId, @PathVariable Long userTripTeamId) {
        UserTripTeam userTripTeam = tripTeamService.getUserTripTeam(userTripTeamId);
        return new UserTripTeamForm(userTripTeam);
    }

    @PostMapping("/{tripTeamId}/invite/{userTripTeamId}/accept")
    @ResponseStatus(HttpStatus.OK)
    public String acceptInvite(@PathVariable Long tripTeamId, @PathVariable Long userTripTeamId, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        tripTeamService.acceptInvite(userTripTeamId, user.getUserId(), tripTeamId);
        return "초대를 수락했습니다";
    }

    @PostMapping("/{tripTeamId}/invite/{userTripTeamId}/refuse")
    @ResponseStatus(HttpStatus.OK)
    public String refuseInvite(@PathVariable Long tripTeamId, @PathVariable Long userTripTeamId, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        tripTeamService.refuseInvite(userTripTeamId, user.getUserId(), tripTeamId);
        return "초대를 거절했습니다";
    }

    @DeleteMapping("/{tripTeamId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTeam(@PathVariable Long tripTeamId, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        tripTeamService.deleteTripTeam(tripTeamId, user.getUserId());
        return "팀 해체가 완료되었습니다.";
    }

    @PostMapping("/{tripTeamId}/addTripPlan")
    @ResponseStatus(HttpStatus.OK)
    public String addTripPlan(@PathVariable Long tripTeamId, @RequestBody TripPlanRequestDto tripPlanRequestDto, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        log.info("tripPlanRequestDto.getStartDate() = {}", tripPlanRequestDto.getStartDate());
        tripPlanService.makeTripPlan(
                user.getUserId(), tripTeamId, tripPlanRequestDto.getPlanName(), tripPlanRequestDto.getPlanContent(), tripPlanRequestDto.getStartDate(), tripPlanRequestDto.getEndDate());
        return "계획 생성이 완료되었습니다.";
    }

    @PostMapping("/{tripTeamId}/{tripPlanId}/addAttraction")
    @ResponseStatus(HttpStatus.OK)
    public void addAttraction(
            @PathVariable Long tripTeamId,
            @PathVariable Long tripPlanId,
            @RequestBody List<AttractionAddRequestDto> attractionInfo,
            HttpServletRequest request
    ) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        List<Integer> attractionIdList = attractionInfo.stream().map(AttractionAddRequestDto::getContentId).collect(Collectors.toList());
        tripPlanService.addPlanAttractions(user.getUserId(), tripTeamId, tripPlanId, attractionIdList);
    }

    @LoginRequired
    @GetMapping("/{tripTeamId}/{tripPlanId}")
    @ResponseStatus(HttpStatus.OK)
    public TripPlanResponseDto getTripPlan(@PathVariable Long tripTeamId,
                                           @PathVariable Long tripPlanId,
                                           HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        TripPlan tripPlan = tripPlanService.getTripPlan(tripPlanId, tripTeamId, user.getUserId());
        return new TripPlanResponseDto(tripPlan);
    }

    @LoginRequired
    @PatchMapping("/{tripTeamId}/{tripPlanId}")
    @ResponseStatus(HttpStatus.OK)
    public String editTripPlan(@PathVariable Long tripTeamId,
                                           @PathVariable Long tripPlanId,
                                           @RequestBody TripPlanRequestDto tripPlanRequestDto,
                                           HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        tripPlanService.editTripPlan(user.getUserId(), tripPlanId, tripTeamId, tripPlanRequestDto);
        return "수정이 완료되었습니다.";
    }

    @DeleteMapping("/{tripTeamId}/{tripPlanId}/{planAttractionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAttraction(@PathVariable Long planAttractionId, @PathVariable Long tripTeamId, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        tripPlanService.deletePlanAttraction(user.getUserId(), planAttractionId, tripTeamId);
    }

    @DeleteMapping("/{tripTeamId}/{tripPlanId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTripPlan(@PathVariable Long tripTeamId, @PathVariable Long tripPlanId, HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        tripPlanService.deleteTripPlan(user.getUserId(), tripPlanId, tripTeamId);
    }

    @GetMapping("/{tripTeamId}/user/leader")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public boolean validLeader(@PathVariable Long tripTeamId, HttpServletRequest request) {
        log.info("validLeader");
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        return tripTeamService.validUserIsLeader(user.getUserId(), tripTeamId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<TripTeamListResponse> getAllTripTeam(HttpServletRequest request) {
        LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
        return tripTeamService.getAllTripTeamByUserId(user.getUserId())
                .stream().map(TripTeamListResponse::new).collect(Collectors.toList());
    }
}
