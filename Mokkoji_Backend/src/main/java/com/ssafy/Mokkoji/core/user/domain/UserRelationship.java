package com.ssafy.Mokkoji.core.user.domain;

import com.ssafy.Mokkoji.domain.BaseTimeEntity;
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
    public UserRelationship(
            final Long userRelationshipId,
            final User user,
            final User targetUser,
            final Relation relation
    ) {
        this.userRelationshipId = userRelationshipId;
        this.user = user;
        this.targetUser = targetUser;
        this.relation = relation;
    }
}
