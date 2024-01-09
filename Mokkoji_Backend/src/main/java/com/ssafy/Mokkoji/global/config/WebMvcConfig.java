package com.ssafy.Mokkoji.global.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.Mokkoji.global.auth.argument.LoginTokenInfoResolver;
import com.ssafy.Mokkoji.global.auth.interceptor.TokenInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final TokenInterceptor tokenInterceptor;

	private final LoginTokenInfoResolver loginTokenInfoResolver;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor)
			.order(1)
			.addPathPatterns("/**")
			.excludePathPatterns("/", "/error", "/attraction/**", "/user/login/**", "/user/signup/**", "/board",
				"/board/{boardId}/comments", "/user/access-token");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOriginPatterns("*")
			.allowedMethods(
				HttpMethod.GET.name(),
				HttpMethod.POST.name(),
				HttpMethod.PUT.name(),
				HttpMethod.DELETE.name(),
				HttpMethod.PATCH.name(),
				HttpMethod.HEAD.name(),
				HttpMethod.OPTIONS.name()
			)
			.allowedHeaders("*")
			.allowCredentials(true)
			.maxAge(3600);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(loginTokenInfoResolver);
	}
}
