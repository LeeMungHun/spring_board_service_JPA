//package com.board.boardservice.domain;
//
//import com.board.boardservice.domain.entity.Board;
//import com.board.boardservice.domain.repository.MemoryBoardRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//class MeomoryBoardRepositoryTest {
//
//    MemoryBoardRepository meomoryBoardRepository = new MemoryBoardRepository();
//
//    @AfterEach
//    public void a(){
//        meomoryBoardRepository.clear();
//    }
//
//
//    @Test
//    void save() {
//        Board board = new Board("제목테스트1", "내용테스트1", "작성자테스트1");
//
//        //저장한 게시글 비교
//        Board save = meomoryBoardRepository.save(board);
//        Board saved = meomoryBoardRepository.findBoard(save.getId());
//
//        assertThat(save.getId()).isEqualTo(saved.getId());
//    }
//
//    @Test
//    void delete() {
//        Board board1 = new Board("제목테스트1", "내용테스트1", "작성자테스트1");
//        Board save1 = meomoryBoardRepository.save(board1);
//
//        Board board2 = new Board("제목테스트2", "내용테스트2", "작성자테스트2");
//        Board save2 = meomoryBoardRepository.save(board2);
//
//        Board board3 = new Board("제목테스트3", "내용테스트3", "작성자테스트3");
//        Board save3 = meomoryBoardRepository.save(board3);
//
//        meomoryBoardRepository.delete(save1.getId());
//        List<Board> all = meomoryBoardRepository.findAll();
//
//        for (Board board : all) {
//            System.out.println("남아있는 게시물 = " + board.getContent());
//        }
//
//        assertThat(all.size()).isEqualTo(2);
//
//    }
//
//    @Test
//    void update() {
//        Board board1 = new Board("제목테스트1", "내용테스트1", "작성자테스트1");
//        Board save1 = meomoryBoardRepository.save(board1);
//
//        Board board2 = new Board("제목테스트2", "내용테스트2", "작성자테스트2");
//        Board save2 = meomoryBoardRepository.save(board2);
//
//        Board board3 = new Board("제목테스트3", "내용테스트3", "작성자테스트3");
//        Board save3 = meomoryBoardRepository.save(board3);
//
//        Board updateBoard = new Board("업데이트", "업데이트", "업데이트");
//
//        meomoryBoardRepository.update(save1.getId(),updateBoard);
//
//        List<Board> all = meomoryBoardRepository.findAll();
//
//        for (Board board : all) {
//            System.out.println("남아있는 게시물 = " + board.getContent());
//        }
//
//        assertThat(all.size()).isEqualTo(3);
//    }
//
//}