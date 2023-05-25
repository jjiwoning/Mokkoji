package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.domain.UserRelationship;
import com.ssafy.enjoytrip.domain.user_relation.Relation;
import com.ssafy.enjoytrip.exception.NotFoundException;
import com.ssafy.enjoytrip.repository.UserRelationshipRepository;
import com.ssafy.enjoytrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRelationshipServiceImpl implements UserRelationshipService {

    private final UserRepository userRepository;

    private final UserRelationshipRepository userRelationshipRepository;

    @Override
    public void makeRelationship(Long userId, Long targetId, Relation relation) {

        if (userRelationshipRepository.existsByUserIdAndTargetId(userId, targetId)) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 사용자 입력"));
        User targetUser = userRepository.findById(targetId)
                .orElseThrow(() -> new NotFoundException("잘못된 사용자 입력"));

        UserRelationship userRelationship = UserRelationship.builder().user(user).targetUser(targetUser).relation(relation).build();

        userRelationshipRepository.save(userRelationship);
    }

    @Override
    public long deleteRelationship(Long userId, Long targetId) {
        return userRelationshipRepository.deleteRelationByUserIdAndTargetId(userId, targetId);
    }

    @Override
    public List<UserRelationship> getAllUserByRelation(Long userId, Relation relation) {
        return userRelationshipRepository.findAllUserByRelation(userId, relation);
    }
}
