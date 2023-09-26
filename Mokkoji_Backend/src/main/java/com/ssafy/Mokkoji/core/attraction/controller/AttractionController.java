package com.ssafy.Mokkoji.core.attraction.controller;

import com.ssafy.Mokkoji.core.attraction.dto.request.AttractionSearch;
import com.ssafy.Mokkoji.core.attraction.dto.response.AttractionResponse;
import com.ssafy.Mokkoji.core.attraction.dto.response.GugunResponse;
import com.ssafy.Mokkoji.core.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionController{

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
