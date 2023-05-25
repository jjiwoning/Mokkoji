package com.ssafy.enjoytrip.token.interceptor;

import com.ssafy.enjoytrip.token.LoginRequired;
import com.ssafy.enjoytrip.util.JwtUtil;
import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.service.UserService;
import com.ssafy.enjoytrip.token.LoginTokenConst;
import com.ssafy.enjoytrip.token.LoginTokenInfo;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;

import static com.ssafy.enjoytrip.token.LoginTokenConst.USER_INFO;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (isPreflightRequest(request)) {
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);

        if (Objects.isNull(loginRequired) && HttpMethod.GET.matches(request.getMethod())) {
            return true;
        }

        String accessToken = request.getHeader(JwtUtil.ACCESS_TOKEN_NAME);

        if (jwtUtil.checkToken(accessToken)) {
            LoginTokenInfo loginTokenInfo = jwtUtil.parseToken(accessToken);
            request.setAttribute(USER_INFO, loginTokenInfo);
            return true;
        }

        String refreshToken = request.getHeader(JwtUtil.REFRESH_TOKEN_NAME);

        if (jwtUtil.checkToken(refreshToken)) {
            LoginTokenInfo loginTokenInfo = jwtUtil.parseToken(refreshToken);
            User user = userService.findUserById(loginTokenInfo.getUserId());

            if (user.getRefreshToken().equals(refreshToken)) {
                String newAccessToken = jwtUtil.createAccessToken(LoginTokenConst.LOGIN_TOKEN, loginTokenInfo);
                request.setAttribute(USER_INFO, loginTokenInfo);
                response.setHeader(JwtUtil.ACCESS_TOKEN_NAME, newAccessToken);
                return true;
            }

        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

    private boolean isPreflightRequest(HttpServletRequest request) {
        return isOptions(request) && hasHeaders(request) && hasMethod(request) && hasOrigin(request);
    }

    private boolean isOptions(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString());
    }

    private boolean hasHeaders(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("Access-Control-Request-Headers"));
    }

    private boolean hasMethod(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("Access-Control-Request-Method"));
    }

    private boolean hasOrigin(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("Origin"));
    }
}
