package com.ssafy.Mokkoji.core.attraction.repository;

import com.ssafy.Mokkoji.core.attraction.domain.AttractionInfo;
import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;

import java.util.List;

public interface AttractionInfoRepositoryCustom {

    List<AttractionInfo> getAllAttractionList(AttractionSearch attractionSearch);

}
