package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.dto.request.AttractionSearch;

import java.util.List;

public interface AttractionInfoRepositoryCustom {

    List<AttractionInfo> getAllAttractionList(AttractionSearch attractionSearch);

}
