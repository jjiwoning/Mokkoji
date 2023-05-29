package com.ssafy.Mokkoji.service;

import com.ssafy.Mokkoji.domain.AttractionInfo;
import com.ssafy.Mokkoji.domain.Gugun;
import com.ssafy.Mokkoji.dto.request.AttractionSearch;

import java.util.List;

public interface AttractionService {
    List<AttractionInfo> getAllAttraction(AttractionSearch attractionSearch);

    List<Gugun> getAllGugunBySidoCode(int sidoCode);
}
