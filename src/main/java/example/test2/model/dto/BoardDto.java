package example.test2.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.test2.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class BoardDto {
    private Integer id;
    private String author;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Builder.Default
    private List<CommentDto> comments = new ArrayList<>();

    public BoardEntity toEntity(){
        return BoardEntity.builder()
        .author(this.author)
        .password(this.password)
        .content(this.content)
        .build();
    }

    public static BoardDto from(BoardEntity boardEntity){
        return BoardDto.builder()
        .id(boardEntity.getId())
        .author(boardEntity.getAuthor())
        .password(boardEntity.getPassword())
        .content(boardEntity.getContent())
        .createdAt(boardEntity.getCreatedAt())
        .updatedAt(boardEntity.getUpdatedAt())
        .build();
    }
}

