package com.ssafy.Mokkoji.core.attraction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;
import com.ssafy.Mokkoji.core.attraction.dto.response.AttractionResponse;
import com.ssafy.Mokkoji.core.attraction.dto.response.GugunResponse;
import com.ssafy.Mokkoji.core.attraction.service.AttractionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionController {

	private final AttractionService attractionService;

	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<AttractionResponse> getAttractionList(@Valid AttractionSearch attractionSearch) {

		return attractionService.getAllAttraction(attractionSearch);
	}

	@GetMapping("/gugun-list")
	@ResponseStatus(HttpStatus.OK)
	public List<GugunResponse> getAllGugun(@RequestParam Integer sidoCode) {

		return attractionService.getAllGugunBySidoCode(sidoCode);
	}
}
