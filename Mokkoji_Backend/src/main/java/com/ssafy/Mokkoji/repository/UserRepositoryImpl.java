package com.ssafy.Mokkoji.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.domain.User;
import com.ssafy.Mokkoji.dto.request.UserSearch;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.Mokkoji.domain.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<User> searchAllUser(UserSearch userSearch) {

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(userSearch.getNickname())) {
            builder.or(user.nickname.contains(userSearch.getNickname()));
        }

        return queryFactory.selectFrom(user)
                .where(builder)
                .limit(userSearch.getSize())
                .offset(userSearch.getOffset())
                .fetch();
    }
}
