package com.ssafy.Mokkoji.core.attraction.repository;

import static com.ssafy.Mokkoji.core.attraction.domain.QAttractionInfo.*;
import static com.ssafy.Mokkoji.core.attraction.domain.QGugun.*;
import static com.ssafy.Mokkoji.core.attraction.domain.QSido.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.Mokkoji.core.attraction.domain.AttractionInfo;
import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttractionInfoRepositoryImpl implements AttractionInfoRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public AttractionInfoRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<AttractionInfo> getAllAttractionList(AttractionSearch attractionSearch) {

		BooleanBuilder searchInfo = makeBooleanBuilder(attractionSearch);

		return queryFactory.selectFrom(attractionInfo)
			.innerJoin(attractionInfo.sido, sido)
			.fetchJoin()
			.innerJoin(attractionInfo.gugun, gugun)
			.fetchJoin()
			.where(searchInfo.and(gugun.sido.eq(sido)))
			.limit(attractionSearch.getSize())
			.offset(attractionSearch.getOffset())
			.orderBy(attractionInfo.title.asc().nullsLast())
			.fetch();
	}

	private BooleanBuilder makeBooleanBuilder(AttractionSearch attractionSearch) {
		BooleanBuilder searchInfo = new BooleanBuilder();

		if (StringUtils.hasText(attractionSearch.getTitle())) {
			searchInfo.and(attractionInfo.title.contains(attractionSearch.getTitle()));
		}

		if (attractionSearch.getContentTypeId() != null) {
			searchInfo.and(attractionInfo.contentTypeId.eq(attractionSearch.getContentTypeId()));
		}

		if (attractionSearch.getSidoCode() != null) {
			searchInfo.and(attractionInfo.sido.sidoCode.eq(attractionSearch.getSidoCode()));
		}

		if (attractionSearch.getGugunCode() != null) {
			searchInfo.and(attractionInfo.gugun.gugunCode.eq(attractionSearch.getGugunCode()));
		}

		return searchInfo;
	}
}
