package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.dto.request.UserSearch;
import com.ssafy.enjoytrip.token.LoginTokenInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    User findUserById(Long userId);

    @Transactional(readOnly = true)
    List<User> findAllUser(UserSearch userSearch);

    LoginTokenInfo loginUser(String loginId, String password);

    void join(User user);

    void updateUser(User user);

    void deleteUser(Long userId);

    void saveRefreshToken(String loginId, String refreshToken);

    void deleteUserRefreshToken(Long userId);

    boolean idDuplicateCheck(String loginId);
}
