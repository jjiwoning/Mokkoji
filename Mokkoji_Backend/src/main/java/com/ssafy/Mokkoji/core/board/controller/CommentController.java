package com.ssafy.Mokkoji.core.board.controller;

import com.ssafy.Mokkoji.core.board.dto.request.CommentRequest;
import com.ssafy.Mokkoji.core.board.dto.response.CommentResponse;
import com.ssafy.Mokkoji.core.board.service.CommentService;
import com.ssafy.Mokkoji.global.auth.LoginTokenInfo;
import com.ssafy.Mokkoji.global.auth.annotation.Authenticated;
import com.ssafy.Mokkoji.global.auth.annotation.LoginRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
			@Authenticated final LoginTokenInfo user
	) {
		commentService.addComment(commentRequest, boardId, user.getUserId());
		return "댓글 등록이 완료되었습니다.";
	}

	@PatchMapping("/{boardId}/comments/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public String updateComment(
			@PathVariable final Long commentId,
		  	@RequestBody final CommentRequest commentRequest,
			@Authenticated final LoginTokenInfo user
	) {
		commentService.editComment(commentId, commentRequest, user.getUserId());
		return "댓글 수정이 완료되었습니다.";
	}

	@DeleteMapping("/{boardId}/comments/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteComment(
			@PathVariable final Long commentId,
			@Authenticated final LoginTokenInfo user
	) {
		commentService.deleteComment(commentId, user.getUserId());
		return "댓글 삭제가 완료되었습니다.";
	}

	@LoginRequired
	@GetMapping("/{boardId}/comments/{commentId}/validWriter")
	@ResponseStatus(HttpStatus.OK)
	public boolean isWriter(
			@PathVariable final Long commentId,
			@Authenticated final LoginTokenInfo user
	) {
		return commentService.isCommentWriter(user.getUserId(), commentId);
	}
}
