package example.test2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.test2.model.repository.BoardRepository;
import example.test2.model.repository.CommentRepository;
import example.test2.model.dto.CommentDto;
import example.test2.model.entity.BoardEntity;
import example.test2.model.entity.CommentEntity;

@Service 
public class CommentService {
    @Autowired private CommentRepository commentRepository;
    @Autowired private BoardRepository boardRepository;

    public boolean save(CommentDto commentDto){
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



    public boolean delete(Integer commentId, String password){
        Optional<CommentEntity> optional = commentRepository.findById(commentId);
        if(optional.isPresent()){
            CommentEntity commentEntity = optional.get();
            commentEntity.getPassword().equals(password);
            commentRepository.deleteById(commentId);
            return true;
        } 
        return false;
    }
}
