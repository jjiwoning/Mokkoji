package com.ssafy.Mokkoji.core.user.service;

import java.util.List;

import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.dto.request.AccessTokenRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserUpdateRequest;
import com.ssafy.Mokkoji.core.user.dto.response.AccessTokenResponse;
import com.ssafy.Mokkoji.core.user.dto.response.RefreshTokenResponse;

public interface UserService {

	User findUserById(Long userId);

	List<User> findAllUser(UserSearchRequest userSearchRequest);

	RefreshTokenResponse loginUser(String loginId, String password);

	AccessTokenResponse makeAccessToken(AccessTokenRequest request);

	Long join(UserJoinRequest request);

	void updateUser(Long userId, UserUpdateRequest request);

	void deleteUser(Long userId);

	void deleteUserRefreshToken(String refreshToken);

	boolean idDuplicateCheck(String loginId);
}
