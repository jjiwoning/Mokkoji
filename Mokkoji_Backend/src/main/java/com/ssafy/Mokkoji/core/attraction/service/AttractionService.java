package com.ssafy.Mokkoji.core.attraction.service;

import com.ssafy.Mokkoji.core.attraction.domain.Gugun;
import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;
import com.ssafy.Mokkoji.core.attraction.dto.response.AttractionResponse;
import com.ssafy.Mokkoji.core.attraction.dto.response.GugunResponse;

import java.util.List;

public interface AttractionService {

    List<AttractionResponse> getAllAttraction(AttractionSearch attractionSearch);

    List<GugunResponse> getAllGugunBySidoCode(int sidoCode);
}
