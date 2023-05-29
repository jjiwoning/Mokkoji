package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.UserRelationship;
import com.ssafy.Mokkoji.domain.user_relation.Relation;

import java.util.List;

public interface UserRelationshipService {
    void makeRelationship(Long userId, Long targetId, Relation relation);

    long deleteRelationship(Long userId, Long targetId);

    List<UserRelationship> getAllUserByRelation(Long userId, Relation relation);
}
