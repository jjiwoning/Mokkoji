package com.ssafy.Mokkoji.core.board.controller;

import java.util.List;

import com.ssafy.Mokkoji.core.board.dto.request.CommentRequest;
import com.ssafy.Mokkoji.core.board.dto.response.CommentResponse;
import com.ssafy.Mokkoji.global.token.LoginRequired;
import com.ssafy.Mokkoji.global.token.LoginTokenInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ssafy.Mokkoji.core.board.service.CommentService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

import static com.ssafy.Mokkoji.global.token.LoginTokenConst.USER_INFO;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/{boardId}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentResponse> getComments(@PathVariable final Long boardId) {
		return commentService.getAllComment(boardId);
	}

	@PostMapping("/{boardId}/comments/write")
	@ResponseStatus(HttpStatus.OK)
	public String registerComment(
			@PathVariable final Long boardId,
			@RequestBody final CommentRequest commentRequest,
			final HttpServletRequest request
	) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		commentService.addComment(commentRequest, boardId, user.getUserId());
		return "댓글 등록이 완료되었습니다.";
	}

	@PatchMapping("/{boardId}/comments/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public String updateComment(
			@PathVariable final Long commentId,
		  	@RequestBody final CommentRequest commentRequest,
		  	final HttpServletRequest request
	) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		commentService.editComment(commentId, commentRequest, user.getUserId());
		return "댓글 수정이 완료되었습니다.";
	}

	@DeleteMapping("/{boardId}/comments/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteComment(
			@PathVariable final Long commentId,
			final HttpServletRequest request
	) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		commentService.deleteComment(commentId, user.getUserId());
		return "댓글 삭제가 완료되었습니다.";
	}

	@LoginRequired
	@GetMapping("/{boardId}/comments/{commentId}/validWriter")
	@ResponseStatus(HttpStatus.OK)
	public boolean isWriter(
			@PathVariable final Long commentId,
			final HttpServletRequest request
	) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		return commentService.isCommentWriter(user.getUserId(), commentId);
	}
}
