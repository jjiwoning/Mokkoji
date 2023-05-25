package com.ssafy.enjoytrip.domain;

import com.ssafy.enjoytrip.domain.user_relation.Relation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRelationship extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRelationshipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private User targetUser;

    @Enumerated(EnumType.STRING)
    private Relation relation; // FOLLOW, BLOCK

    @Builder
    public UserRelationship(Long userRelationshipId, User user, User targetUser, Relation relation) {
        this.userRelationshipId = userRelationshipId;
        this.user = user;
        this.targetUser = targetUser;
        this.relation = relation;
    }
}
