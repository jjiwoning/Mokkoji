package com.ssafy.Mokkoji.core.user.controller;

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

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public TokenResponse login(@RequestBody @Valid final UserLoginRequest userLoginRequest) {

		LoginTokenInfo loginTokenInfo = userService.loginUser(userLoginRequest.getLoginId(), userLoginRequest.getPassword());

		String accessToken = jwtUtil.createAccessToken(LoginTokenConst.LOGIN_TOKEN, loginTokenInfo);
		String refreshToken = jwtUtil.createRefreshToken(LoginTokenConst.LOGIN_TOKEN, loginTokenInfo);

		userService.saveRefreshToken(userLoginRequest.getLoginId(), refreshToken);

		return TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).message("Create Token").build();
	}

	@GetMapping("/signup/duplicate")
	@ResponseStatus(HttpStatus.OK)
	public boolean duplicateCheck(@ModelAttribute(name = "loginId") final String loginId) {
		log.info("loginId = {}", loginId);
		return userService.idDuplicateCheck(loginId);
	}

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.OK)
	public String join(@RequestBody @Valid final UserJoinRequest userJoinRequest) {
		userService.join(userJoinRequest);
		return "회원 가입이 완료되었습니다.";
	}

	@PatchMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public String updateUser(
			@PathVariable final long userId,
			@RequestBody @Valid final UserUpdateRequest userUpdateRequest,
			final HttpServletRequest request
	) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		if (userInfo.getUserId() != userId) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		userService.updateUser(userUpdateRequest);
		return "회원 정보 수정이 완료되었습니다.";
	}

	@GetMapping("/getUserId")
	@LoginRequired
	public UserIdResponse getUserId(final HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		return new UserIdResponse(userInfo.getUserId());
	}

	@GetMapping("/{userId}")
	@LoginRequired
	@ResponseStatus(HttpStatus.OK)
	public User getUserDetail(
			@PathVariable final long userId,
			final HttpServletRequest request
	) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		if (userInfo.getUserId() != userId) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		return userService.findUserById(userId);
	}

	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse> getAllUser(@Valid final UserSearchRequest userSearchRequest) {
		List<User> allUser = userService.findAllUser(userSearchRequest);
		return allUser.stream().map(UserResponse::new).collect(Collectors.toList());
	}

	@DeleteMapping("/delete")
	@ResponseStatus(HttpStatus.OK)
	public String deleteUser(final HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		userService.deleteUser(userInfo.getUserId());
		return "회원 탈퇴가 완료되었습니다.";
	}

	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.OK)
	public String logout(final HttpServletRequest request) {
		LoginTokenInfo userInfo = (LoginTokenInfo) request.getAttribute(USER_INFO);
		userService.deleteUserRefreshToken(userInfo.getUserId());
		return "로그아웃이 완료되었습니다.";
	}

	@GetMapping("/relationship/{relation}")
	@ResponseStatus(HttpStatus.OK)
	public List<RelationshipResponse> getAllRelation(
			@PathVariable final Relation relation,
			final HttpServletRequest request
	) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		List<UserRelationship> findRelation = userRelationshipService.getAllUserByRelation(user.getUserId(), relation);
		return findRelation.stream().map(RelationshipResponse::new).collect(Collectors.toList());
	}

	@PostMapping("/relationship/{targetId}")
	@ResponseStatus(HttpStatus.OK)
	public String addRelationship(
			@PathVariable final Long targetId,
			@RequestBody final Relation relation,
			final HttpServletRequest request
	) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		userRelationshipService.makeRelationship(user.getUserId(), targetId, relation);
		return "요청 완료";
	}

	@DeleteMapping("/relationship/{targetId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteRelationship(
			@PathVariable final Long targetId,
			final HttpServletRequest request
	) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		long count = userRelationshipService.deleteRelationship(user.getUserId(), targetId);
		if (count == 0) {
			throw new IllegalArgumentException("잘못된 요청입니다.");
		}
		return "요청 완료";
	}
}