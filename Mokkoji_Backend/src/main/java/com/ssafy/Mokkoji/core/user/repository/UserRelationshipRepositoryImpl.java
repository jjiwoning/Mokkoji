package com.ssafy.Mokkoji.core.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.ssafy.Mokkoji.core.user.domain.QUserRelationship.userRelationship;


public class UserRelationshipRepositoryImpl implements UserRelationshipRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRelationshipRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public boolean existsByUserIdAndTargetId(Long userId, Long targetId) {
        return queryFactory.selectFrom(userRelationship)
                .where(userRelationship.user.userId.eq(userId).and(userRelationship.targetUser.userId.eq(targetId)))
                .fetchFirst() != null;
    }

    @Override
    public long deleteRelationByUserIdAndTargetId(Long userId, Long targetId) {
        return queryFactory.delete(userRelationship)
                .where(userRelationship.user.userId.eq(userId).and(userRelationship.targetUser.userId.eq(targetId)))
                .execute();
    }
}
