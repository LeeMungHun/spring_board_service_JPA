package com.board.boardservice.service;

import com.board.boardservice.domain.entity.Board;
import com.board.boardservice.domain.repository.JpaBoardRepository;
import com.board.boardservice.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public void delete(Long boardId) {
        jpaBoard.deleteById(boardId);
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

    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4; // 한 페이지에 존재하는 게시글 수


    @Transactional
    public List<BoardDto> findAll(Integer pageNum) {
        Page<Board> page = jpaBoard.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "id")));

        List<Board> boards = page.getContent();
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

    @Transactional
    public Long getBoardCount() {
        return jpaBoard.count();
    }

    //이 함수는 직접 만든게 아니라 가져왔음.
    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }


    @Transactional
    public List<BoardDto> searchTitle(String searchTitle) {
        List<Board> boards = jpaBoard.findByTitleContaining(searchTitle);
        boards = boards.subList(0, 4);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(boards.isEmpty()) return boardDtoList;

        for (Board board : boards) {
            boardDtoList.add(
                    BoardDto.builder()
                            .id(board.getId())
                            .title(board.getTitle())
                            .content(board.getContent())
                            .writer(board.getWriter())
                            .build()
            );

        }
        return boardDtoList;
    }

}
