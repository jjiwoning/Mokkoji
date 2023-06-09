package com.ssafy.Mokkoji.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.Mokkoji.dto.response.CommentResponseDto;
import com.ssafy.Mokkoji.token.LoginRequired;
import com.ssafy.Mokkoji.token.LoginTokenInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ssafy.Mokkoji.domain.Comment;
import com.ssafy.Mokkoji.service.CommentService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

import static com.ssafy.Mokkoji.token.LoginTokenConst.USER_INFO;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	//댓글 목록
	@GetMapping("/{boardId}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentResponseDto> getComments(@PathVariable Long boardId) {
		return commentService.getAllComment(boardId).stream()
				.map(CommentResponseDto::new)
				.collect(Collectors.toList());
	}

	//댓글 등록
	@PostMapping("{boardId}/comments/write")
	@ResponseStatus(HttpStatus.OK)
	public String registComment(@PathVariable Long boardId, @RequestBody Comment comment, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		commentService.addComment(comment.getContent(), boardId, user.getUserId());
		return "댓글 등록이 완료되었습니다.";
	}


	//댓글 수정
	@PatchMapping("{boardId}/comments/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public String modifyComment(@PathVariable Long commentId,
											  @RequestBody Comment comment,
											  HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		commentService.editComment(commentId, comment.getContent(), user.getUserId());
		return "댓글 수정이 완료되었습니다.";

	}

	//댓글 삭제
	@DeleteMapping("{boardId}/comments/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteComment(@PathVariable Long commentId, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		commentService.deleteComment(commentId, user.getUserId());
		return "댓글 삭제가 완료되었습니다.";
	}

	@LoginRequired
	@GetMapping("/{boardId}/comments/{commentId}/validWriter")
	@ResponseStatus(HttpStatus.OK)
	public boolean isWriter(@PathVariable Long commentId, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		return commentService.isCommentWriter(user.getUserId(), commentId);
	}
}
