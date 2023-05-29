package com.ssafy.Mokkoji.repository;

public interface UserRelationshipRepositoryCustom {
    boolean existsByUserIdAndTargetId(Long userId, Long targetId);

    long deleteRelationByUserIdAndTargetId(Long userId, Long targetId);
}
