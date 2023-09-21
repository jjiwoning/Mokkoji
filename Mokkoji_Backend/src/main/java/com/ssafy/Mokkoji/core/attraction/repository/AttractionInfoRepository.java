package com.ssafy.Mokkoji.core.attraction.repository;

import com.ssafy.Mokkoji.core.attraction.domain.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer>, AttractionInfoRepositoryCustom {
}
