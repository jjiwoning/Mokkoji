package com.ssafy.Mokkoji.core.user.service;

import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserUpdateRequest;
import com.ssafy.Mokkoji.global.auth.LoginTokenInfo;

import java.util.List;

public interface UserService {

    User findUserById(Long userId);

    List<User> findAllUser(UserSearchRequest userSearchRequest);

    LoginTokenInfo loginUser(String loginId, String password);

    void join(UserJoinRequest request);

    void updateUser(UserUpdateRequest request);

    void deleteUser(Long userId);

    void saveRefreshToken(String loginId, String refreshToken);

    void deleteUserRefreshToken(Long userId);

    boolean idDuplicateCheck(String loginId);
}
