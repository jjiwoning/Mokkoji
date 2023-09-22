package com.ssafy.Mokkoji.core.user.service;

import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.domain.UserRelationship;
import com.ssafy.Mokkoji.core.user.domain.Relation;
import com.ssafy.Mokkoji.global.exception.NotFoundException;
import com.ssafy.Mokkoji.core.user.repository.UserRelationshipRepository;
import com.ssafy.Mokkoji.core.user.repository.UserRepository;
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
    public void makeRelationship(
            final Long userId,
            final Long targetId,
            final Relation relation
    ) {
        if (userRelationshipRepository.existsByUserIdAndTargetId(userId, targetId)) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        User user = getUserById(userId);
        User targetUser = getUserById(targetId);

        UserRelationship userRelationship = UserRelationship.builder()
                .user(user)
                .targetUser(targetUser)
                .relation(relation)
                .build();

        userRelationshipRepository.save(userRelationship);
    }

    private User getUserById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("잘못된 사용자 입력"));
    }

    @Override
    public long deleteRelationship(final Long userId, final Long targetId) {
        return userRelationshipRepository.deleteRelationByUserIdAndTargetId(userId, targetId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRelationship> getAllUserByRelation(final Long userId, final Relation relation) {
        return userRelationshipRepository.findAllUserByRelation(userId, relation);
    }
}
