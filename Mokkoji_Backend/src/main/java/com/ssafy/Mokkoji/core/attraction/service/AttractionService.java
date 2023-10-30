package com.ssafy.Mokkoji.core.attraction.service;

import java.util.List;

import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;
import com.ssafy.Mokkoji.core.attraction.dto.response.AttractionResponse;
import com.ssafy.Mokkoji.core.attraction.dto.response.GugunResponse;

public interface AttractionService {

	List<AttractionResponse> getAllAttraction(AttractionSearch attractionSearch);

	List<GugunResponse> getAllGugunBySidoCode(int sidoCode);
}
