package com.ssafy.Mokkoji.core.user.service;

import com.ssafy.Mokkoji.core.user.domain.UserRelationship;
import com.ssafy.Mokkoji.core.user.domain.Relation;

import java.util.List;

public interface UserRelationshipService {
    void makeRelationship(Long userId, Long targetId, Relation relation);

    long deleteRelationship(Long userId, Long targetId);

    List<UserRelationship> getAllUserByRelation(Long userId, Relation relation);
}
