package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
