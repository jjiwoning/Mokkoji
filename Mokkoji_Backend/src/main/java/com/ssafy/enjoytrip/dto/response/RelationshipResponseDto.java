package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.UserRelationship;
import com.ssafy.enjoytrip.domain.user_relation.Relation;
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
