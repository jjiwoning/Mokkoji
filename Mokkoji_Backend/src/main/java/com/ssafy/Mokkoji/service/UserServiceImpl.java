package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.User;
import com.ssafy.Mokkoji.dto.request.UserSearch;
import com.ssafy.Mokkoji.exception.LoginException;
import com.ssafy.Mokkoji.exception.NotFoundException;
import com.ssafy.Mokkoji.repository.UserRepository;
import com.ssafy.Mokkoji.token.LoginTokenInfo;
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
    public User findUserById(Long userId) {
        return getUser(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUser(UserSearch userSearch) {
        return userRepository.searchAllUser(userSearch);
    }

    @Override
    public LoginTokenInfo loginUser(String loginId, String password) {
        User findUser = getUserByLoginId(loginId);

        if (findUser.login(password)) {
            return new LoginTokenInfo(findUser.getUserId(), findUser.getNickname());
        }

        throw new LoginException("잘못된 아이디 또는 비밀번호를 입력했습니다.");
    }

    @Override
    public void join(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        User findUser = getUser(user.getUserId());
        findUser.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void saveRefreshToken(String loginId, String refreshToken) {
        User user = getUserByLoginId(loginId);
        user.addRefreshToken(refreshToken);
    }

    @Override
    public void deleteUserRefreshToken(Long userId) {
        User user = getUser(userId);
        user.addRefreshToken(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean idDuplicateCheck(String loginId) {
        return !userRepository.existsByLoginId(loginId);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("회원 정보가 존재하지 않습니다."));
    }

    private User getUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new LoginException("잘못된 아이디 또는 비밀번호를 입력했습니다."));
    }
}
