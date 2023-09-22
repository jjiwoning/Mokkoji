package com.ssafy.Mokkoji.core.user.controller;


import com.ssafy.Mokkoji.core.trip.domain.UserTripTeam;
import com.ssafy.Mokkoji.core.trip.dto.response.UserTripTeamForm;
import com.ssafy.Mokkoji.core.trip.service.TripTeamService;
import com.ssafy.Mokkoji.core.user.domain.Relation;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.domain.UserRelationship;
import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserLoginRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserUpdateRequest;
import com.ssafy.Mokkoji.core.user.dto.response.RelationshipResponse;
import com.ssafy.Mokkoji.core.user.dto.response.TokenResponse;
import com.ssafy.Mokkoji.core.user.dto.response.UserIdResponse;
import com.ssafy.Mokkoji.core.user.dto.response.UserResponse;
import com.ssafy.Mokkoji.core.user.infra.JwtUtil;
import com.ssafy.Mokkoji.core.user.service.UserRelationshipService;
import com.ssafy.Mokkoji.core.user.service.UserService;
import com.ssafy.Mokkoji.global.token.LoginRequired;
import com.ssafy.Mokkoji.global.token.LoginTokenConst;
import com.ssafy.Mokkoji.global.token.LoginTokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.Mokkoji.global.token.LoginTokenConst.USER_INFO;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final UserRelationshipService userRelationshipService;

	private final JwtUtil jwtUtil;

	//로그인
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public TokenResponse login(@RequestBody @Valid UserLoginRequest userLoginRequest) {

		LoginTokenInfo loginTokenInfo = userService.loginUser(userLoginRequest.getLoginId(), userLoginRequest.getPassword());

		String accessToken = jwtUtil.createAccessToken(LoginTokenConst.LOGIN_TOKEN, loginTokenInfo);
		String refreshToken = jwtUtil.createRefreshToken(LoginTokenConst.LOGIN_TOKEN, loginTokenInfo);

		userService.saveRefreshToken(userLoginRequest.getLoginId(), refreshToken);

		return TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).message("Create Token").build();
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
	@ResponseStatus(HttpStatus.OK)
	public String join(@RequestBody @Valid UserJoinRequest userJoinRequest) {
		User user = User.builder()
				.name(userJoinRequest.getName())
				.nickname(userJoinRequest.getNickname())
				.loginId(userJoinRequest.getLoginId())
				.mail(userJoinRequest.getMail())
				.password(userJoinRequest.getPassword())
				.phoneNumber(userJoinRequest.getPhoneNumber())
				.build();
		userService.join(user);
		return "회원 가입이 완료되었습니다.";
	}

	//회원 정보 수정
	@PatchMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public String updateUser(@PathVariable long userId,
										@RequestBody @Valid UserUpdateRequest userUpdateRequest,
										HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		if (userInfo.getUserId() != userId) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		User user = userUpdateRequest.toEntity();
		userService.updateUser(user);
		return "회원 정보 수정이 완료되었습니다.";
	}

	@GetMapping("/getUserId")
	@LoginRequired
	public UserIdResponse getUserId(HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		return new UserIdResponse(userInfo.getUserId());
	}

	//회원 상세 정보 조회
	@GetMapping("/{userId}")
	@LoginRequired
	@ResponseStatus(HttpStatus.OK)
	public User getUserDetail(@PathVariable long userId, HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		if (userInfo.getUserId() != userId) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		return userService.findUserById(userId);
	}

	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse> getAllUser(@Valid UserSearchRequest userSearchRequest) {
		List<User> allUser = userService.findAllUser(userSearchRequest);
		return allUser.stream().map(UserResponse::new).collect(Collectors.toList());
	}

	//회원 정보 삭제
	@DeleteMapping("/delete")
	@ResponseStatus(HttpStatus.OK)
	public String deleteUser(HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		userService.deleteUser(userInfo.getUserId());
		return "회원 탈퇴가 완료되었습니다.";
	}

	//로그 아웃
	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.OK)
	public String logout(HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		userService.deleteUserRefreshToken(userInfo.getUserId());
		return "로그아웃이 완료되었습니다.";
	}

	@GetMapping("/relationship/{relation}")
	@ResponseStatus(HttpStatus.OK)
	public List<RelationshipResponse> getAllRelation(@PathVariable Relation relation, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		List<UserRelationship> findRelation = userRelationshipService.getAllUserByRelation(user.getUserId(), relation);
		return findRelation.stream().map(RelationshipResponse::new).collect(Collectors.toList());
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