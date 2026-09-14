package example.totalpractice.model.dto;

import example.totalpractice.model.entity.ProductsEntity;
import example.totalpractice.model.entity.ReviewsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class ReviewsDto {
    private Integer rno;
    private Integer bno;
    private String reviewer;
    private String content;
    private int rating;

    public ReviewsEntity toEntity(ProductsEntity productEntity) {
        return ReviewsEntity.builder()
                .reviewer(this.reviewer)
                .content(this.content)
                .rating(this.rating)
                .productEntity(productEntity)
                .build();
    }

    public static ReviewsDto from(ReviewsEntity entity) {
        return ReviewsDto.builder()
                .rno(entity.getRno())
                .bno(entity.getProductEntity().getBno())
                .reviewer(entity.getReviewer())
                .content(entity.getContent())
                .rating(entity.getRating())
                .build();
    }
}