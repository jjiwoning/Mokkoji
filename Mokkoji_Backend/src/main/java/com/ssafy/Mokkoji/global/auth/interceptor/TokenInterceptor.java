package com.ssafy.Mokkoji.global.auth.interceptor;

import static com.ssafy.Mokkoji.global.auth.LoginTokenConst.*;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.Mokkoji.core.user.domain.TokenProvider;
import com.ssafy.Mokkoji.global.auth.annotation.LoginRequired;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

	private final TokenProvider tokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		if (isPreflightRequest(request)) {
			return true;
		}

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod)handler;
		LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);

		if (Objects.isNull(loginRequired) && HttpMethod.GET.matches(request.getMethod())) {
			return true;
		}

		String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);

		System.out.println(accessToken);

		if (tokenProvider.validateToken(accessToken)) {
			Long userId = tokenProvider.parseToken(accessToken);
			request.setAttribute(USER_INFO, userId);
			return true;
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
