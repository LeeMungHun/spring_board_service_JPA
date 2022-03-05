package com.board.boardservice.dto;

import com.board.boardservice.domain.entity.Board;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private String writer;

    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
