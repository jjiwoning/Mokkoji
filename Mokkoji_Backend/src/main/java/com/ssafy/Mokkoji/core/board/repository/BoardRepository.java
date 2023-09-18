package com.ssafy.Mokkoji.core.board.repository;

import com.ssafy.Mokkoji.core.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
