package example.totalpractice.model.dto;

import example.totalpractice.model.entity.CategoryEntity;
import example.totalpractice.model.entity.ProductsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDto {
    private Integer bno;
    private String name;
    private Integer price;
    private Integer cno;

    public ProductsEntity toEntity() {
        return ProductsEntity.builder()
                .name(this.name)
                .price(this.price)
                .build();
    }

    public static ProductResponseDto from(ProductsEntity entity) {
        return ProductResponseDto.builder()
                .bno(entity.getBno())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }
}