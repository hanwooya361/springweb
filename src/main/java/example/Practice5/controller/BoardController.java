package example.Practice5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice5.model.dto.BoardDto;
import example.Practice5.service.BoardService;

@RestController 
@RequestMapping("/api/board")
public class BoardController {
    @Autowired private BoardService boardService;

    // 등록
    @PostMapping("")
    public boolean boardsave(@RequestBody BoardDto boardDto){
        return boardService.boardsave(boardDto);
    }
    // 조회
    @GetMapping("")
    public List<BoardDto> boardDetail(BoardDto boardDto){
        return boardService.boardDetail(boardDto);
    }
    // 삭제
    @DeleteMapping("")
    public boolean boardDelete(@RequestParam(name="id") Integer boardId, @RequestParam(name="password") Integer password){
        return boardService.boardDelete(boardId , password);
    }
}
