package com.ssafy.Mokkoji.core.user.service;

import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.dto.request.UserJoinRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;
import com.ssafy.Mokkoji.core.user.dto.request.UserUpdateRequest;
import com.ssafy.Mokkoji.core.user.repository.UserRepository;
import com.ssafy.Mokkoji.global.exception.LoginException;
import com.ssafy.Mokkoji.global.exception.NotFoundException;
import com.ssafy.Mokkoji.global.auth.LoginTokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
    public LoginTokenInfo loginUser(final String loginId, final String password) {
        User findUser = getUserByLoginId(loginId);

        if (findUser.login(password)) {
            return new LoginTokenInfo(findUser.getUserId(), findUser.getNickname());
        }

        throw new LoginException("잘못된 아이디 또는 비밀번호를 입력했습니다.");
    }

    @Override
    public void join(final UserJoinRequest request) {
        userRepository.save(request.toEntity());
    }

    @Override
    public void updateUser(final UserUpdateRequest request) {
        User findUser = getUser(request.getUserId());
        findUser.updateUser(
                request.getMail(),
                request.getNickname(),
                request.getPassword(),
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
        return !userRepository.existsByLoginId(loginId);
    }

    private User getUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("회원 정보가 존재하지 않습니다."));
    }

    private User getUserByLoginId(final String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new LoginException("잘못된 아이디 또는 비밀번호를 입력했습니다."));
    }
}
