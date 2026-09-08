package example.Practice5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice5.model.dto.CommentDto;
import example.Practice5.service.CommentService;

@RestController 
@RequestMapping("/api/board/comments")
public class CommentController {
    @Autowired private CommentService commentService;

    // 등록
    @PostMapping("")
    public boolean commentsave(@RequestBody CommentDto commentDto){
        return commentService.commentsave(commentDto);
    }
    // 삭제
    @DeleteMapping("")
    public boolean commentDelete(@RequestParam (name="commentId") Integer commentId
                                ,@RequestParam (name="password") String password){
        return commentService.commentDelete(commentId, password);
    }
}
