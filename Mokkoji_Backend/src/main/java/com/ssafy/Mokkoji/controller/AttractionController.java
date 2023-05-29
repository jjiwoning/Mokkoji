package com.ssafy.Mokkoji.controller;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.Mokkoji.dto.response.AttractionListResponseDto;
import com.ssafy.Mokkoji.dto.response.GugunResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ssafy.Mokkoji.domain.AttractionInfo;
import com.ssafy.Mokkoji.dto.request.AttractionSearch;
import com.ssafy.Mokkoji.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionController{

	private final AttractionService attractionService;

	//목록 조회
	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<AttractionListResponseDto> getAttractionList(@Valid AttractionSearch attractionSearch) {

		List<AttractionInfo> list = attractionService.getAllAttraction(attractionSearch);

		return list.stream()
				.map(AttractionListResponseDto::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/gugun-list")
	@ResponseStatus(HttpStatus.OK)
	public List<GugunResponseDto> getAllGugun(@RequestParam Integer sidoCode) {

		return attractionService.getAllGugunBySidoCode(sidoCode)
				.stream().map(GugunResponseDto::new).collect(Collectors.toList());
	}
}
