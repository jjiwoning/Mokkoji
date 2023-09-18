package com.ssafy.Mokkoji.core.board.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import com.ssafy.Mokkoji.core.board.dto.response.BoardDetailResponseDto;
import com.ssafy.Mokkoji.core.board.dto.response.BoardListResponseDto;
import com.ssafy.Mokkoji.token.LoginRequired;
import com.ssafy.Mokkoji.util.FileStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ssafy.Mokkoji.core.board.domain.Board;
import com.ssafy.Mokkoji.core.board.dto.request.BoardSearch;
import com.ssafy.Mokkoji.core.board.service.BoardService;
import com.ssafy.Mokkoji.token.LoginTokenInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static com.ssafy.Mokkoji.token.LoginTokenConst.*;


@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	private final FileStore fileStore;

	@GetMapping("/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	public BoardDetailResponseDto getBoardDetail(@PathVariable("boardId") Long boardId) {
		Board board = boardService.getBoardDetail(boardId);
		return new BoardDetailResponseDto(board);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<BoardListResponseDto> getBoardList(@Valid BoardSearch boardSearch) {
		return boardService.getAllBoards(boardSearch)
				.stream().map(BoardListResponseDto::new)
				.collect(Collectors.toList());
	}

	@PostMapping("/write")
	@ResponseStatus(HttpStatus.OK)
	public String registerBoard(@RequestParam String title, @RequestParam String content, @RequestParam(value = "images", required = false) List<MultipartFile> images, HttpServletRequest request) throws IOException {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		List<BoardImage> boardImages = fileStore.storeImages(images);
		Board board = Board.builder().title(title).content(content).build();

		log.info("board = {}, {}, {}", board, user, images);

		boardService.addBoard(board, user.getUserId(), boardImages);

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
	protected String modifyBoard(@PathVariable long boardId, @RequestParam String title, @RequestParam String content, @RequestParam(value = "images", required = false) List<MultipartFile> images, HttpServletRequest request) throws IOException {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		Board board = boardService.getBoardDetail(boardId);
		if (!board.getUser().getUserId().equals(user.getUserId())) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		List<BoardImage> boardImages = fileStore.storeImages(images);
		boardService.updateBoard(boardId, title, content, boardImages);
		return "게시글 수정이 완료되었습니다";
	}

	@DeleteMapping("/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteBoard(@PathVariable long boardId, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		log.info("user = {}", user);
		boardService.deleteBoard(boardId, user.getUserId());
		return "게시글 삭제가 완료되었습니다";
	}

	@LoginRequired
	@GetMapping("/{boardId}/validWriter")
	@ResponseStatus(HttpStatus.OK)
	public boolean isWriter(@PathVariable long boardId, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		return boardService.isBoardWriter(user.getUserId(), boardId);
	}
}
