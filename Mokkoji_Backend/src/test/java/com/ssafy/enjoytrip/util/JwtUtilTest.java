package com.ssafy.enjoytrip.util;

import com.ssafy.enjoytrip.token.LoginTokenConst;
import com.ssafy.enjoytrip.token.LoginTokenInfo;
import io.jsonwebtoken.Claims;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    JwtUtil jwtUtil = new JwtUtil();

    @Test
    @DisplayName("토큰 인코딩 및 디코딩 확인 테스트")
    void test1() {
        LoginTokenInfo loginTokenInfo = new LoginTokenInfo();
        loginTokenInfo.setNickName("테스트");
        loginTokenInfo.setUserId(10L);

        String accessToken = jwtUtil.createAccessToken(LoginTokenConst.LOGIN_TOKEN, loginTokenInfo);

        System.out.println(accessToken);

        LoginTokenInfo loginTokenInfo1 = jwtUtil.parseToken(accessToken);

        System.out.println(loginTokenInfo1.getNickName());
        System.out.println(loginTokenInfo1.getUserId());

        assertThat(loginTokenInfo1.getNickName()).isEqualTo(loginTokenInfo.getNickName());
        assertThat(loginTokenInfo1.getUserId()).isEqualTo(loginTokenInfo.getUserId());
    }
}