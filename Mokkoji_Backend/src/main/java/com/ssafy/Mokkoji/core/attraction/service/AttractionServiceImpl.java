package com.ssafy.Mokkoji.core.attraction.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;
import com.ssafy.Mokkoji.core.attraction.dto.response.AttractionResponse;
import com.ssafy.Mokkoji.core.attraction.dto.response.GugunResponse;
import com.ssafy.Mokkoji.core.attraction.repository.AttractionInfoRepository;
import com.ssafy.Mokkoji.core.attraction.repository.GugunRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

	private final AttractionInfoRepository attractionInfoRepository;

	private final GugunRepository gugunRepository;

	@Override
	public List<AttractionResponse> getAllAttraction(final AttractionSearch attractionSearch) {
		return attractionInfoRepository.getAllAttractionList(attractionSearch).stream()
			.map(AttractionResponse::new)
			.collect(Collectors.toList());
	}

	@Override
	public List<GugunResponse> getAllGugunBySidoCode(final int sidoCode) {
		return gugunRepository.getAllGugunBySidoCode(sidoCode).stream()
			.map(GugunResponse::new)
			.collect(Collectors.toList());
	}
}
