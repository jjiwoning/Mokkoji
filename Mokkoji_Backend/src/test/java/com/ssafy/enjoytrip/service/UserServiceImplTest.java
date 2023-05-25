package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.dto.request.UserUpdateDto;
import com.ssafy.enjoytrip.exception.LoginException;
import com.ssafy.enjoytrip.token.LoginTokenInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    @DisplayName("유저 조회 테스트")
    void findUserById() {
        User user = User.builder().loginId("Hello").password("hello2").nickname("안녕").build();
        userService.join(user);
        em.flush();
        User findUser = userService.findUserById(user.getUserId());
        Assertions.assertThat(findUser.getNickname()).isEqualTo("안녕");
    }

    @Test
    @Transactional
    @DisplayName("로그인 성공 처리")
    void loginUser1() {
        User user = User.builder().loginId("Hello").password("hello2").nickname("안녕").build();
        userService.join(user);
        em.flush();
        LoginTokenInfo session = userService.loginUser(user.getLoginId(), user.getPassword());
        Assertions.assertThat(session.getNickName()).isEqualTo("안녕");
    }

    @Test
    @Transactional
    @DisplayName("로그인 실패 처리")
    void loginUser2() {
        User user = User.builder().loginId("Hello").password("hello2").nickname("안녕").build();
        userService.join(user);
        em.flush();
        Assertions.assertThatThrownBy(() -> userService.loginUser(user.getLoginId(), "jhe"))
                .isInstanceOf(LoginException.class).hasMessage("잘못된 아이디 또는 비밀번호를 입력했습니다.");
    }

    @Test
    @Transactional
    @DisplayName("회원 가입 테스트")
    void join() {
        User user = User.builder().loginId("Hello").password("hello2").nickname("안녕").build();
        userService.join(user);
    }

    @Test
    @Transactional
    @DisplayName("회원 정보 수정 테스트")
    void updateUser() {
        User user = User.builder().loginId("Hello").password("hello2").nickname("안녕").build();
        userService.join(user);
        em.flush();
        UserUpdateDto userUpdateDto = new UserUpdateDto(user.getUserId(), "asd@asd.com", "안녕2", "hello3", "01012345678");
        User userEntity = userUpdateDto.toEntity();
        User findUser = userService.findUserById(user.getUserId());
        findUser.updateUser(userEntity);
        em.flush();
        User findUser2 = userService.findUserById(user.getUserId());
        Assertions.assertThat(findUser2.getNickname()).isEqualTo("안녕2");
    }
}