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
    public String boards(Model model) {
        List<BoardDto> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId,Model model){
        BoardDto boardDto = boardService.findBoard(boardId);
        model.addAttribute("board", boardDto);
        return "board/board";
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
    }


}
