package com.ssafy.enjoytrip.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.dto.request.AttractionSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.enjoytrip.domain.QAttractionInfo.*;
import static com.ssafy.enjoytrip.domain.QGugun.*;
import static com.ssafy.enjoytrip.domain.QSido.*;

@Slf4j
public class AttractionInfoRepositoryImpl implements AttractionInfoRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public AttractionInfoRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AttractionInfo> getAllAttractionList(AttractionSearch attractionSearch) {

        BooleanBuilder builder = makeBooleanBuilder(attractionSearch);

        return queryFactory.selectFrom(attractionInfo)
                .innerJoin(attractionInfo.sido, sido).fetchJoin()
                .innerJoin(attractionInfo.gugun, gugun).fetchJoin()
                .where(builder.and(gugun.sido.eq(sido)))
                .limit(attractionSearch.getSize())
                .offset(attractionSearch.getOffset())
                .orderBy(attractionInfo.title.asc().nullsLast())
                .fetch();
    }

    private BooleanBuilder makeBooleanBuilder(AttractionSearch attractionSearch) {
        BooleanBuilder builder = new BooleanBuilder();

        log.info("{}", attractionSearch.getTitle());
        log.info("{}", attractionSearch.getContentTypeId());
        log.info("{}", attractionSearch.getGugunCode());

        if (StringUtils.hasText(attractionSearch.getTitle())) {
            builder.and(attractionInfo.title.contains(attractionSearch.getTitle()));
        }

        if (attractionSearch.getContentTypeId() != null) {
            builder.and(attractionInfo.contentTypeId.eq(attractionSearch.getContentTypeId()));
        }

        if (attractionSearch.getSidoCode() != null) {
            builder.and(attractionInfo.sido.sidoCode.eq(attractionSearch.getSidoCode()));
        }

        if (attractionSearch.getGugunCode() != null) {
            builder.and(attractionInfo.gugun.gugunCode.eq(attractionSearch.getGugunCode()));
        }

        return builder;
    }
}
