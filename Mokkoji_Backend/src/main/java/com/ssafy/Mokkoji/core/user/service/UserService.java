package com.ssafy.Mokkoji.core.user.service;

import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;
import com.ssafy.Mokkoji.token.LoginTokenInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    User findUserById(Long userId);

    @Transactional(readOnly = true)
    List<User> findAllUser(UserSearchRequest userSearchRequest);

    LoginTokenInfo loginUser(String loginId, String password);

    void join(User user);

    void updateUser(User user);

    void deleteUser(Long userId);

    void saveRefreshToken(String loginId, String refreshToken);

    void deleteUserRefreshToken(Long userId);

    boolean idDuplicateCheck(String loginId);
}
