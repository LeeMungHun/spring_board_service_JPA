package com.board.boardservice.domain.repository;

import com.board.boardservice.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardRepository extends JpaRepository<Board,Long> {
}
