package com.ssafy.Mokkoji.core.attraction.service;

import com.ssafy.Mokkoji.core.attraction.domain.AttractionInfo;
import com.ssafy.Mokkoji.core.attraction.domain.Gugun;
import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;

import java.util.List;

public interface AttractionService {
    List<AttractionInfo> getAllAttraction(AttractionSearch attractionSearch);

    List<Gugun> getAllGugunBySidoCode(int sidoCode);
}
