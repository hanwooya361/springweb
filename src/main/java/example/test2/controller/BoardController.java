package example.test2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.test2.model.dto.BoardDto;
import example.test2.service.BoardService;

@RestController 
@RequestMapping("/api/board")
public class BoardController {
    @Autowired private BoardService boardService;

    @PostMapping ("")
    public boolean save(@RequestBody BoardDto boardDto){
        return boardService.save(boardDto);
    }

    @GetMapping ("")
    public List<BoardDto> findAll(){
        return boardService.findAll();
    }

    @DeleteMapping ("")
    public boolean delete(@RequestParam (name="id") Integer Id, @RequestParam (name="password") String password){
        return boardService.delete(Id, password);
    }
}
