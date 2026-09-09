package example.test2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.test2.model.dto.BoardDto;
import example.test2.model.dto.CommentDto;
import example.test2.model.entity.BoardEntity;
import example.test2.model.entity.CommentEntity;
import example.test2.model.repository.BoardRepository;

@Service 
public class BoardService {
    @Autowired private BoardRepository boardRepository;

    public boolean save(BoardDto boardDto){
        BoardEntity boardEntity = boardDto.toEntity();
        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);
        if(savedBoardEntity.getId() >= 1){return true;}
        return false;
    }

    public List<BoardDto> findAll(){
        // return boardRepository.findAll().stream().map((entity)->{return BoardDto.from(entity);}).toList();
        // return boardRepository.findAll().stream().map(BoardDto::from).toList();
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        boardEntities.forEach((boardentity)->{
            BoardDto boardDto = BoardDto.from(boardentity);
            boardentity.getCommentList().forEach((commententity)->{
                CommentDto commentDto = CommentDto.from(commententity);
                boardDto.getComments().add(commentDto);
            });
            boardDtos.add(boardDto);
        });
        return boardDtos;
    }

    public boolean delete(Integer Id, String password){
        Optional<BoardEntity> optional = boardRepository.findById(Id);
        if(optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            boardEntity.getPassword().equals(password);
            boardRepository.deleteById(Id);
            return true;
        } 
        return false;
    }
}
