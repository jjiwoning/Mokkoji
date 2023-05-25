package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.UserRelationship;
import com.ssafy.enjoytrip.domain.user_relation.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRelationshipRepository extends JpaRepository<UserRelationship, Long>, UserRelationshipRepositoryCustom {

    @Query("select r from UserRelationship r join fetch r.user join fetch r.targetUser where r.user.userId = :id and r.relation = :relation")
    List<UserRelationship> findAllUserByRelation(@Param("id") Long userId, @Param("relation") Relation relation);

}
