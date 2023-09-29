package com.ssafy.Mokkoji.core.board.controller;

import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.core.board.dto.response.BoardListResponse;
import com.ssafy.Mokkoji.core.board.dto.response.BoardResponse;
import com.ssafy.Mokkoji.core.board.service.BoardService;
import com.ssafy.Mokkoji.global.auth.LoginTokenInfo;
import com.ssafy.Mokkoji.global.auth.annotation.Authenticated;
import com.ssafy.Mokkoji.global.auth.annotation.LoginRequired;
import com.ssafy.Mokkoji.global.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	private final FileStore fileStore;

	@GetMapping("/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	public BoardResponse getBoardDetail(@PathVariable("boardId") final Long boardId) {
		return new BoardResponse(boardService.getBoardDetail(boardId));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<BoardListResponse> getBoardList(@Valid final BoardSearch boardSearch) {
		return boardService.getAllBoards(boardSearch)
				.stream().map(BoardListResponse::new)
				.collect(Collectors.toList());
	}

	@PostMapping("/write")
	@ResponseStatus(HttpStatus.OK)
	public String registerBoard(
			@RequestParam final String title,
			@RequestParam final String content,
			@RequestParam(value = "images", required = false) final List<MultipartFile> images,
			@Authenticated final LoginTokenInfo user
	) throws IOException {
		List<BoardImage> boardImages = fileStore.storeImages(images);

		boardService.addBoard(title, content, user.getUserId(), boardImages);

		return "게시글 등록이 완료되었습니다";
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/images/{fileName}")
	public Resource getImages(@PathVariable String fileName) throws MalformedURLException {
		log.info("fileName = {}", fileName);
		return new UrlResource("file:" + fileStore.getFilePath(fileName));
	}

	@PatchMapping("/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	protected String modifyBoard(
			@PathVariable final Long boardId,
			@RequestParam final String title,
			@RequestParam final String content,
			@RequestParam(value = "images", required = false) final List<MultipartFile> images,
			@Authenticated final LoginTokenInfo user
	) throws IOException {
		boardService.updateBoard(boardId, title, content, images, user.getUserId());
		return "게시글 수정이 완료되었습니다";
	}

	@DeleteMapping("/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteBoard(
			@PathVariable final Long boardId,
			@Authenticated final LoginTokenInfo user
	) {
		boardService.deleteBoard(boardId, user.getUserId());
		return "게시글 삭제가 완료되었습니다";
	}

	@LoginRequired
	@GetMapping("/{boardId}/validWriter")
	@ResponseStatus(HttpStatus.OK)
	public boolean isWriter(
			@PathVariable final Long boardId,
			@Authenticated final LoginTokenInfo user
	) {
		return boardService.isBoardWriter(user.getUserId(), boardId);
	}
}
