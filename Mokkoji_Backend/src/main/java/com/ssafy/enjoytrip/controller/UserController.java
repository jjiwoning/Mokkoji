package com.ssafy.enjoytrip.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ssafy.enjoytrip.dto.response.*;
import com.ssafy.enjoytrip.token.LoginRequired;
import com.ssafy.enjoytrip.util.JwtUtil;
import com.ssafy.enjoytrip.domain.UserRelationship;
import com.ssafy.enjoytrip.domain.UserTripTeam;
import com.ssafy.enjoytrip.domain.user_relation.Relation;
import com.ssafy.enjoytrip.dto.request.UserJoinDto;
import com.ssafy.enjoytrip.dto.request.UserSearch;
import com.ssafy.enjoytrip.service.TripTeamService;
import com.ssafy.enjoytrip.service.UserRelationshipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.dto.request.UserLoginDto;
import com.ssafy.enjoytrip.dto.request.UserUpdateDto;
import com.ssafy.enjoytrip.service.UserService;
import com.ssafy.enjoytrip.token.LoginTokenConst;
import com.ssafy.enjoytrip.token.LoginTokenInfo;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.enjoytrip.token.LoginTokenConst.USER_INFO;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final TripTeamService tripTeamService;

	private final UserRelationshipService userRelationshipService;

	private final JwtUtil jwtUtil;

	//로그인
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public TokenResponseDto login(@RequestBody @Valid UserLoginDto userLoginDto) {

		LoginTokenInfo loginTokenInfo = userService.loginUser(userLoginDto.getLoginId(), userLoginDto.getPassword());

		String accessToken = jwtUtil.createAccessToken(LoginTokenConst.LOGIN_TOKEN, loginTokenInfo);
		String refreshToken = jwtUtil.createRefreshToken(LoginTokenConst.LOGIN_TOKEN, loginTokenInfo);

		userService.saveRefreshToken(userLoginDto.getLoginId(), refreshToken);

		return TokenResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken).message("Create Token").build();
	}

	// 중복 체크
	@GetMapping("/signup/duplicate")
	@ResponseStatus(HttpStatus.OK)
	public boolean duplicateCheck(@ModelAttribute(name = "loginId") String loginId) {
		log.info("loginId = {}", loginId);
		return userService.idDuplicateCheck(loginId);
	}

	//회원 가입
	@PostMapping("/signup")
	public ResponseEntity<?> join(@RequestBody @Valid UserJoinDto userJoinDto) {
		User user = User.builder()
				.name(userJoinDto.getName())
				.nickname(userJoinDto.getNickname())
				.loginId(userJoinDto.getLoginId())
				.mail(userJoinDto.getMail())
				.password(userJoinDto.getPassword())
				.phoneNumber(userJoinDto.getPhoneNumber())
				.build();
		userService.join(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//회원 정보 수정
	@PatchMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable long userId,
										@RequestBody @Valid UserUpdateDto userUpdateDto,
										HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		if (userInfo.getUserId() != userId) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		User user = userUpdateDto.toEntity();
		userService.updateUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getUserId")
	@LoginRequired
	public UserIdResponseDto getUserId(HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		return new UserIdResponseDto(userInfo.getUserId());
	}

	//회원 상세 정보 조회
	@GetMapping("/{userId}")
	@LoginRequired
	public ResponseEntity<User> getUserDetail(@PathVariable long userId, HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		if (userInfo.getUserId() != userId) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		User user = userService.findUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponseDto> getAllUser(@Valid UserSearch userSearch) {
		List<User> allUser = userService.findAllUser(userSearch);
		return allUser.stream().map(UserResponseDto::new).collect(Collectors.toList());
	}

	//회원 정보 삭제
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		userService.deleteUser(userInfo.getUserId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//로그 아웃
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		userService.deleteUserRefreshToken(userInfo.getUserId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/invite")
	@LoginRequired
	@ResponseStatus(HttpStatus.OK)
	public List<UserTripTeamForm> allInviteInfo(HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		log.info("user = {}", user.getUserId());
		List<UserTripTeam> allUserTripTeam = tripTeamService.getAllUserTripTeam(user.getUserId());
		log.info("allUserTripTeam = {}", allUserTripTeam);
		return allUserTripTeam.stream().map(UserTripTeamForm::new).collect(Collectors.toList());
	}

	@GetMapping("/relationship/{relation}")
	@ResponseStatus(HttpStatus.OK)
	public List<RelationshipResponseDto> getAllRelation(@PathVariable Relation relation, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		List<UserRelationship> findRelation = userRelationshipService.getAllUserByRelation(user.getUserId(), relation);
		return findRelation.stream().map(RelationshipResponseDto::new).collect(Collectors.toList());
	}

	@PostMapping("/relationship/{targetId}")
	@ResponseStatus(HttpStatus.OK)
	public String addRelationship(@PathVariable Long targetId, @RequestBody Relation relation, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		userRelationshipService.makeRelationship(user.getUserId(), targetId, relation);
		return "요청 완료";
	}

	@DeleteMapping("/relationship/{targetId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteRelationship(@PathVariable Long targetId, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		long count = userRelationshipService.deleteRelationship(user.getUserId(), targetId);
		if (count == 0) {
			throw new IllegalArgumentException("잘못된 요청입니다.");
		}
		return "요청 완료";
	}
}