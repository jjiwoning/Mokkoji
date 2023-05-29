package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.AttractionInfo;
import com.ssafy.Mokkoji.dto.request.AttractionSearch;

import java.util.List;

public interface AttractionInfoRepositoryCustom {

    List<AttractionInfo> getAllAttractionList(AttractionSearch attractionSearch);

}
