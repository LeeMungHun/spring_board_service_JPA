package com.board.boardservice.service;

import com.board.boardservice.domain.entity.Board;
import com.board.boardservice.domain.repository.JpaBoardRepository;
import com.board.boardservice.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final JpaBoardRepository jpaBoard;

    @Transactional
    public Board save(BoardDto boardDto) {
        return jpaBoard.save(boardDto.toEntity());
    }
    @Transactional
    public void update(Long boardId, BoardDto boardDto) {
        Optional<Board> byId = jpaBoard.findById(boardId);
        Board board = byId.get();
        board.setContent(boardDto.getContent());
        board.setTitle(boardDto.getTitle());
        board.setWriter(boardDto.getWriter());
        jpaBoard.save(board);
    }

    @Transactional
    public BoardDto findBoard(Long boardId) {
        Optional<Board> byId = jpaBoard.findById(boardId);
        Board board = byId.get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .writer(board.getWriter())
                .content(board.getContent())
                .build();

        return boardDto;
    }
    @Transactional
    public List<BoardDto> findAll() {
        List<Board> boards = jpaBoard.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boards) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .writer(board.getWriter())
                    .content(board.getContent())
                    .build();

            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
}
