package com.ssafy.enjoytrip.controller;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.enjoytrip.dto.response.CommentResponseDto;
import com.ssafy.enjoytrip.token.LoginRequired;
import com.ssafy.enjoytrip.token.LoginTokenInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.enjoytrip.domain.Comment;
import com.ssafy.enjoytrip.service.CommentService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

import static com.ssafy.enjoytrip.token.LoginTokenConst.USER_INFO;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	//댓글 목록
	@GetMapping("/{boardId}/comments")
	@ResponseStatus(HttpStatus.OK)
	protected List<CommentResponseDto> getComments(@PathVariable long boardId) {
		return commentService.getAllComment(boardId).stream()
				.map(CommentResponseDto::new)
				.collect(Collectors.toList());
	}

	//댓글 등록
	@PostMapping("{boardId}/comments/write")
	protected ResponseEntity<?> registComment(@PathVariable long boardId, @RequestBody Comment comment, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		commentService.addComment(comment.getContent(), boardId, user.getUserId());
		return new ResponseEntity<>(HttpStatus.OK);
	}


	//댓글 수정
	@PatchMapping("{boardId}/comments/{commentId}")
	protected ResponseEntity<?> modifyComment(@PathVariable long commentId,
											  @RequestBody Comment comment,
											  HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		commentService.editComment(commentId, comment.getContent(), user.getUserId());
		return new ResponseEntity<List<Book>>(HttpStatus.OK);

	}

	//댓글 삭제
	@DeleteMapping("{boardId}/comments/{commentId}")
	protected ResponseEntity<?> deleteComment(@PathVariable long commentId, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		commentService.deleteComment(commentId, user.getUserId());
		return new ResponseEntity<List<Book>>(HttpStatus.OK);
	}

	@LoginRequired
	@GetMapping("/{boardId}/comments/{commentId}/validWriter")
	@ResponseStatus(HttpStatus.OK)
	public boolean isWriter(@PathVariable long commentId, HttpServletRequest request) {
		LoginTokenInfo user = (LoginTokenInfo) request.getAttribute(USER_INFO);
		return commentService.isCommentWriter(user.getUserId(), commentId);
	}
}
