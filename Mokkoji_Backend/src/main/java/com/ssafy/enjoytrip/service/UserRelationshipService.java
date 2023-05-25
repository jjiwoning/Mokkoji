package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.UserRelationship;
import com.ssafy.enjoytrip.domain.user_relation.Relation;

import java.util.List;

public interface UserRelationshipService {
    void makeRelationship(Long userId, Long targetId, Relation relation);

    long deleteRelationship(Long userId, Long targetId);

    List<UserRelationship> getAllUserByRelation(Long userId, Relation relation);
}
