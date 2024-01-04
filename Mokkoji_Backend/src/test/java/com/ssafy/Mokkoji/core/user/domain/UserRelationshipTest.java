package com.ssafy.Mokkoji.core.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import helper.domain.UserDomainHelper;

@DisplayName("UserRelationship 단위 테스트")
class UserRelationshipTest {

	@Test
	@DisplayName("유저가 유저를 차단할 수 있다.")
	void block() {
		// given
		User user = UserDomainHelper.buildDefaultUser().build();
		User targetUser = UserDomainHelper.buildDefaultUser().build();

		// when
		UserRelationship userRelationship = UserRelationship.builder()
			.user(user)
			.targetUser(targetUser)
			.relation(Relation.BLOCK)
			.build();

		// then
		Assertions.assertThat(userRelationship).extracting(UserRelationship::getRelation).isEqualTo(Relation.BLOCK);
	}

	@Test
	@DisplayName("유저가 유저를 팔로우할 수 있다.")
	void follow() {
		// given
		User user = UserDomainHelper.buildDefaultUser().build();
		User targetUser = UserDomainHelper.buildDefaultUser().build();

		// when
		UserRelationship userRelationship = UserRelationship.builder()
			.user(user)
			.targetUser(targetUser)
			.relation(Relation.FOLLOW)
			.build();

		// then
		Assertions.assertThat(userRelationship).extracting(UserRelationship::getRelation).isEqualTo(Relation.FOLLOW);
	}
}