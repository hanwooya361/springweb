package example.test2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.test2.model.dto.CommentDto;
import example.test2.service.CommentService;

@RestController 
@RequestMapping("/api/board/comments")
public class CommentController {
    @Autowired private CommentService commentService;

    @PostMapping("")
    public boolean save(@RequestBody CommentDto commentDto){
        return commentService.save(commentDto);
    }

    @DeleteMapping ("")
    public boolean delete(@RequestParam (name="commentId") Integer commentId, @RequestParam (name="password") String password){
        return commentService.delete(commentId, password);
    }
}
