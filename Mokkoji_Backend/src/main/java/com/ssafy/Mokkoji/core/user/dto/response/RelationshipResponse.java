package com.ssafy.Mokkoji.core.user.dto.response;

import com.ssafy.Mokkoji.core.user.domain.Relation;
import com.ssafy.Mokkoji.core.user.domain.UserRelationship;

import lombok.Data;

@Data
public class RelationshipResponse {

	private Long userRelationshipId;

	private Long userId;

	private String nickname;

	private Relation relation; // FOLLOW, BLOCK

	public RelationshipResponse(UserRelationship userRelationship) {
		this.userRelationshipId = userRelationship.getUserRelationshipId();
		this.userId = userRelationship.getUser().getUserId();
		this.nickname = userRelationship.getUser().getNickname().getValue();
		this.relation = userRelationship.getRelation();
	}
}
