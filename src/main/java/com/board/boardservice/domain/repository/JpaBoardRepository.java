package com.board.boardservice.domain.repository;

import com.board.boardservice.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByTitleContaining(String searchTitle);
}
