package com.ssafy.Mokkoji.core.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.Mokkoji.core.user.domain.PasswordEncoder;
import com.ssafy.Mokkoji.core.user.domain.PasswordValidator;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.domain.vo.LoginId;
import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserUpdateRequest;
import com.ssafy.Mokkoji.core.user.exception.LoginFailException;
import com.ssafy.Mokkoji.core.user.repository.UserRepository;
import com.ssafy.Mokkoji.global.auth.LoginTokenInfo;
import com.ssafy.Mokkoji.global.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final PasswordValidator passwordValidator;

	@Override
	@Transactional(readOnly = true)
	public User findUserById(final Long userId) {
		return getUser(userId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAllUser(final UserSearchRequest userSearchRequest) {
		return userRepository.searchAllUser(userSearchRequest);
	}

	@Override
	public LoginTokenInfo loginUser(final String loginId, final String rawPassword) {
		User findUser = getUserByLoginId(loginId);

		findUser.login(rawPassword, passwordEncoder);

		return new LoginTokenInfo(findUser.getUserId(), findUser.getNickname().getValue());
	}

	@Override
	public Long join(final UserJoinRequest request) {
		passwordValidator.validatePassword(request.getPassword());

		User user = User.builder()
			.loginId(request.getLoginId())
			.mail(request.getMail())
			.phoneNumber(request.getPhoneNumber())
			.name(request.getName())
			.nickname(request.getNickname())
			.encodedPassword(passwordEncoder.encode(request.getPassword()))
			.build();

		return userRepository.save(user).getUserId();
	}

	@Override
	public void updateUser(final Long userId, final UserUpdateRequest request) {
		passwordValidator.validatePassword(request.getPassword());
		
		User findUser = getUser(userId);
		findUser.updateUser(
			request.getMail(),
			request.getNickname(),
			passwordEncoder.encode(request.getPassword()),
			request.getPhoneNumber()
		);
	}

	@Override
	public void deleteUser(final Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public void saveRefreshToken(final String loginId, final String refreshToken) {
		User user = getUserByLoginId(loginId);
		user.addRefreshToken(refreshToken);
	}

	@Override
	public void deleteUserRefreshToken(final Long userId) {
		User user = getUser(userId);
		user.addRefreshToken(null);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean idDuplicateCheck(final String loginId) {
		return !userRepository.existsByLoginId(LoginId.from(loginId));
	}

	private User getUser(final Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundException("회원 정보가 존재하지 않습니다."));
	}

	private User getUserByLoginId(final String loginId) {
		return userRepository.findByLoginId(LoginId.from(loginId))
			.orElseThrow(LoginFailException::new);
	}
}
