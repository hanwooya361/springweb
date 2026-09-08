import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Practice5.model.dto.BoardDto;
import example.Practice5.model.dto.CommentDto;
import example.Practice5.model.entity.BoardEntity;
import example.Practice5.model.repository.BoardRepository;
import example.Practice5.service.BoardService;
import example.Practice5.service.CommentService;

@Service 
public class C {
    @Autowired private BoardRepository boardRepository;
    
    public boolean  save(BoardDto boardDto){
        BoardEntity boardEntity = boardDto.toEntity();
        BoardEntity entity = boardRepository.save(boardDto);
        if(entity.getId() >= 1){return true;}
        return false;
    }

    public List<BoardDto> findAll(BoardDto boardDto){
        List<BoardEntity> boardentity = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        boardentity.forEach((boardentitys)->{
            BoardDto dto = BoardDto.from(boardentitys);
            boardentitys.getCommentList().forEach((comment)->{
                CommentDto cdto = CommentDto.from(comment);
                dto.getComments().add(cdto);
            });
            boardDtos.add(dto);
        });
        return boardDtos;
    }
}
