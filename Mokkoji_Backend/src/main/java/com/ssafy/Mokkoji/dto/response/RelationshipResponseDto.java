package com.ssafy.Mokkoji.dto.response;

import com.ssafy.Mokkoji.core.user.domain.UserRelationship;
import com.ssafy.Mokkoji.core.user.domain.Relation;
import lombok.Data;

@Data
public class RelationshipResponseDto {

    private Long userRelationshipId;

    private Long userId;

    private String nickname;

    private Relation relation; // FOLLOW, BLOCK

    public RelationshipResponseDto(UserRelationship userRelationship) {
        this.userRelationshipId = userRelationship.getUserRelationshipId();
        this.userId = userRelationship.getUser().getUserId();
        this.nickname = userRelationship.getUser().getNickname();
        this.relation = userRelationship.getRelation();
    }
}
