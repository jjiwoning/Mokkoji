package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
