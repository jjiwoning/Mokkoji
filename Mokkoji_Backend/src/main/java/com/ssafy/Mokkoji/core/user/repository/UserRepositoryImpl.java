package com.ssafy.Mokkoji.core.user.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.Mokkoji.core.user.domain.QUser.user;


public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<User> searchAllUser(UserSearchRequest userSearchRequest) {

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(userSearchRequest.getNickname())) {
            builder.or(user.nickname.contains(userSearchRequest.getNickname()));
        }

        return queryFactory.selectFrom(user)
                .where(builder)
                .limit(userSearchRequest.getSize())
                .offset(userSearchRequest.getOffset())
                .fetch();
    }
}
