package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.domain.Gugun;
import com.ssafy.enjoytrip.dto.request.AttractionSearch;

import java.util.List;

public interface AttractionService {
    List<AttractionInfo> getAllAttraction(AttractionSearch attractionSearch);

    List<Gugun> getAllGugunBySidoCode(int sidoCode);
}
