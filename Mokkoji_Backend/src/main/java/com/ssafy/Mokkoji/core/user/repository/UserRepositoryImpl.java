package com.ssafy.Mokkoji.core.user.repository;

import static com.ssafy.Mokkoji.core.user.domain.QUser.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;

public class UserRepositoryImpl implements UserRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public UserRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<User> searchAllUser(UserSearchRequest userSearchRequest) {

		BooleanBuilder builder = new BooleanBuilder();

		if (StringUtils.hasText(userSearchRequest.getNickname())) {
			builder.or(user.nickname.value.contains(userSearchRequest.getNickname()));
		}

		return queryFactory.selectFrom(user)
			.where(builder)
			.limit(userSearchRequest.getSize())
			.offset(userSearchRequest.getOffset())
			.fetch();
	}
}
