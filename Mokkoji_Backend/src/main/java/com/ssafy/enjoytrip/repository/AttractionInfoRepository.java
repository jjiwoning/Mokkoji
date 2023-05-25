package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer>, AttractionInfoRepositoryCustom {
}
