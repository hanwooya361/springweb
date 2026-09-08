package example.Practice5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Practice5.model.dto.BoardDto;
import example.Practice5.model.dto.CommentDto;
import example.Practice5.model.entity.BoardEntity;
import example.Practice5.model.repository.BoardRepository;

@Service 
public class BoardService {
    @Autowired private BoardRepository boardRepository;

    // 등록
    public boolean boardsave(BoardDto boardDto){
        BoardEntity boardEntity = boardDto.toEntity();
        BoardEntity savedEntity = boardRepository.save(boardEntity);
        if(savedEntity.getId() >= 1){return true;}
        return false;
    }
    // 조회
    public List<BoardDto> boardDetail(){
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        boardEntities.forEach((boardentity)->{
            BoardDto dto = BoardDto.from(boardentity);
            boardentity.getCommentList().forEach((commententity)->{
                CommentDto commentDto = CommentDto.from(commententity);
                dto.getComments().add(commentDto);
            });
            boardDtos.add(dto);
        });
        return boardDtos;
    }

        // 삭제
        public boolean boardDelete(Integer boardId, String password){
            Optional<BoardEntity> optional = boardRepository.findById(boardId);
            if(optional.isPresent()){
                BoardEntity boardEntity = optional.get();
                if(boardEntity.getPassword().equals(password)){
                    boardRepository.deleteById(boardId);
                    return true;
                }
                return false;
            }
            return false;
        }

}
