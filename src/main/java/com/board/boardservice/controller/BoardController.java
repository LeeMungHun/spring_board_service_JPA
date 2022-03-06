package com.board.boardservice.controller;

import com.board.boardservice.domain.entity.Board;
import com.board.boardservice.dto.BoardDto;
import com.board.boardservice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
        private final BoardService boardService;

    @GetMapping
    public String boards(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boards = boardService.findAll(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);
        model.addAttribute("boards", boards);
        model.addAttribute("pageList", pageList);
        return "board/boards";
    }

    @GetMapping("/search")
    public String searchBoard(@RequestParam String searchTitle,Model model){
        List<BoardDto> boardDtoList = boardService.searchTitle(searchTitle);
        model.addAttribute("boards", boardDtoList);
        return "board/boards";
    }


    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId,Model model){
        BoardDto boardDto = boardService.findBoard(boardId);
        model.addAttribute("board", boardDto);
        return "board/board";
    }

    @PostMapping("/{boardId}")
    public String delete(@PathVariable Long boardId){
        boardService.delete(boardId);
        return "redirect:/boards";
    }

    @GetMapping("/write")
    public String addForm() {
        return "board/addForm";
    }

    @PostMapping("/write")
    public String writeBoard(@ModelAttribute BoardDto boardDto, RedirectAttributes redirectAttributes) {
        Board saveBoard = boardService.save(boardDto);
        redirectAttributes.addAttribute("boardId", saveBoard.getId());
        redirectAttributes.addAttribute("add_status", true);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId,Model model) {
        BoardDto boardDto = boardService.findBoard(boardId);
        model.addAttribute("board", boardDto);
        return "board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId,@ModelAttribute BoardDto boardDto,
                       RedirectAttributes redirectAttributes) {
        boardService.update(boardId, boardDto);
        redirectAttributes.addAttribute("edit_status", true);
        return "redirect:/boards/{boardId}";
    }



    @PostConstruct
    public void init(){
        boardService.save(new BoardDto(10L,"title1", "content1", "작성자1"));
        boardService.save(new BoardDto(11L,"title2", "content2", "작성자2"));
        boardService.save(new BoardDto(10L,"title1", "content1", "작성자1"));
        boardService.save(new BoardDto(11L,"title2", "content2", "작성자2"));
        boardService.save(new BoardDto(10L,"title1", "content1", "작성자1"));
        boardService.save(new BoardDto(11L,"title2", "content2", "작성자2"));
        boardService.save(new BoardDto(10L,"title1", "content1", "작성자1"));
        boardService.save(new BoardDto(11L,"title2", "content2", "작성자2"));
        boardService.save(new BoardDto(10L,"title1", "content1", "작성자1"));
        boardService.save(new BoardDto(11L,"title2", "content2", "작성자2"));
        boardService.save(new BoardDto(10L,"title1", "content1", "작성자1"));
        boardService.save(new BoardDto(11L,"title2", "content2", "작성자2"));
    }
}
