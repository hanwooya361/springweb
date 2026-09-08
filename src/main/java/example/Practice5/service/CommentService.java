package example.Practice5.service;

import example.Practice5.model.repository.BoardRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Practice5.model.dto.CommentDto;
import example.Practice5.model.entity.BoardEntity;
import example.Practice5.model.entity.CommentEntity;
import example.Practice5.model.repository.CommentRepository;

@Service 
public class CommentService {
    @Autowired private BoardRepository boardRepository;
    @Autowired private CommentRepository commentRepository;


    // 등록
    public boolean commentsave(CommentDto commentDto){
        CommentEntity commentEntity = commentDto.toEntity();
        Optional<BoardEntity> optional = boardRepository.findById(commentDto.getBoardId());
        if(optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            commentEntity.setBoardEntity(boardEntity);
            commentRepository.save(commentEntity);
            return true;
        }
        return false;
    }
    // 삭제
    public boolean commentDelete(Integer commentId, String password){
        Optional<CommentEntity> optional = commentRepository.findById(commentId);
        if(optional.isPresent()){
            CommentEntity commentEntity = optional.get();
            if(commentEntity.getPassword().equals(password)){
                commentRepository.deleteById(commentId);
                return true;
            }
            return false;
        }
        return false;
    }

}
