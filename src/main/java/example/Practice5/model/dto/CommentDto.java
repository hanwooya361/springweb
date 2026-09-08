package example.Practice5.model.dto;

import java.time.LocalDateTime;

import example.Practice5.model.entity.CommentEntity;
import example.Practice5.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class CommentDto {
    private Integer commentId;
    private String author;
    private Integer password;
    private String content;
    private  LocalDateTime createdAt;
    private  LocalDateTime updateAt;
    private Integer boardId;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
        .author(this.author)
        .password(this.password)
        .content(this.content)
        .build();
    }

    public static CommentDto from(CommentEntity commentEntity){
        return CommentDto.builder()
        .commentId(commentEntity.getCommentId())
        .author(commentEntity.getAuthor())
        .password(commentEntity.getPassword())
        .content(commentEntity.getContent())
        .createdAt(commentEntity.getCreatedAt())
        .updateAt(commentEntity.getUpdatedAt())
        .boardId(commentEntity.getBoardEntity().getBoardId())
        .build();
    }
    
}