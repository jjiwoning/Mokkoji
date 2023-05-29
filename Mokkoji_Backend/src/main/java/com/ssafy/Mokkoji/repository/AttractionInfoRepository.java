package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer>, AttractionInfoRepositoryCustom {
}
