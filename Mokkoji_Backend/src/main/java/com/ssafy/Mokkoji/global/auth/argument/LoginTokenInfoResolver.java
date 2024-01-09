package com.ssafy.Mokkoji.global.auth.argument;

import static com.ssafy.Mokkoji.global.auth.LoginTokenConst.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ssafy.Mokkoji.global.auth.LoginTokenInfo;
import com.ssafy.Mokkoji.global.auth.annotation.Authenticated;

@Component
public class LoginTokenInfoResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(final MethodParameter parameter) {
		return parameter.getParameterType().equals(LoginTokenInfo.class)
			&& parameter.hasParameterAnnotation(Authenticated.class);
	}

	@Override
	public Object resolveArgument(
		final MethodParameter parameter,
		final ModelAndViewContainer mavContainer,
		final NativeWebRequest webRequest,
		final WebDataBinderFactory binderFactory
	) {
		return getLoginTokenInfo(webRequest);
	}

	private LoginTokenInfo getLoginTokenInfo(NativeWebRequest webRequest) {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		validateRequest(request);
		return new LoginTokenInfo((Long)request.getAttribute(USER_INFO));
	}

	private void validateRequest(HttpServletRequest request) {
		if (request == null) {
			throw new IllegalStateException();
		}
		if (request.getAttribute(USER_INFO) == null) {
			throw new IllegalStateException();
		}
	}
}
