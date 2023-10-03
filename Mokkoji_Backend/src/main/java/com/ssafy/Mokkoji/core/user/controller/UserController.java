package com.ssafy.Mokkoji.core.user.controller;

import com.ssafy.Mokkoji.core.user.domain.Relation;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.domain.UserRelationship;
import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserLoginRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserUpdateRequest;
import com.ssafy.Mokkoji.core.user.dto.response.*;
import com.ssafy.Mokkoji.core.user.infra.JwtUtil;
import com.ssafy.Mokkoji.core.user.service.UserRelationshipService;
import com.ssafy.Mokkoji.core.user.service.UserService;
import com.ssafy.Mokkoji.global.auth.LoginTokenConst;
import com.ssafy.Mokkoji.global.auth.LoginTokenInfo;
import com.ssafy.Mokkoji.global.auth.annotation.Authenticated;
import com.ssafy.Mokkoji.global.auth.annotation.LoginRequired;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
			@Authenticated final LoginTokenInfo user
	) {
		if (user.getUserId() != userId) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		userService.updateUser(userUpdateRequest);
		return "회원 정보 수정이 완료되었습니다.";
	}

	@GetMapping("/getUserId")
	@LoginRequired
	public UserIdResponse getUserId(@Authenticated final LoginTokenInfo user) {
		return new UserIdResponse(user.getUserId());
	}

	@GetMapping("/{userId}")
	@LoginRequired
	@ResponseStatus(HttpStatus.OK)
	public UserDetailResponse getUserDetail(
			@PathVariable final Long userId,
			@Authenticated final LoginTokenInfo user
	) {
		if (!Objects.equals(user.getUserId(), userId)) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		return UserDetailResponse.of(userService.findUserById(userId));
	}

	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse> getAllUser(@Valid final UserSearchRequest userSearchRequest) {
		List<User> allUser = userService.findAllUser(userSearchRequest);
		return allUser.stream().map(UserResponse::new).collect(Collectors.toList());
	}

	@DeleteMapping("/delete")
	@ResponseStatus(HttpStatus.OK)
	public String deleteUser(@Authenticated final LoginTokenInfo user) {
		userService.deleteUser(user.getUserId());
		return "회원 탈퇴가 완료되었습니다.";
	}

	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.OK)
	public String logout(@Authenticated final LoginTokenInfo user) {
		userService.deleteUserRefreshToken(user.getUserId());
		return "로그아웃이 완료되었습니다.";
	}

	@GetMapping("/relationship/{relation}")
	@ResponseStatus(HttpStatus.OK)
	public List<RelationshipResponse> getAllRelation(
			@PathVariable final Relation relation,
			@Authenticated final LoginTokenInfo user
	) {
		List<UserRelationship> findRelation = userRelationshipService.getAllUserByRelation(user.getUserId(), relation);
		return findRelation.stream().map(RelationshipResponse::new).collect(Collectors.toList());
	}

	@PostMapping("/relationship/{targetId}")
	@ResponseStatus(HttpStatus.OK)
	public String addRelationship(
			@PathVariable final Long targetId,
			@RequestBody final Relation relation,
			@Authenticated final LoginTokenInfo user
	) {
		userRelationshipService.makeRelationship(user.getUserId(), targetId, relation);
		return "요청 완료";
	}

	@DeleteMapping("/relationship/{targetId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteRelationship(
			@PathVariable final Long targetId,
			@Authenticated final LoginTokenInfo user
	) {
		long count = userRelationshipService.deleteRelationship(user.getUserId(), targetId);
		if (count == 0) {
			throw new IllegalArgumentException("잘못된 요청입니다.");
		}
		return "요청 완료";
	}
}