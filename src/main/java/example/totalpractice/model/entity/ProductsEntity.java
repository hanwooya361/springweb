package example.totalpractice.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@NoArgsConstructor 
@AllArgsConstructor 
@Data 
@Builder 
@Table (name = "product")
public class ProductsEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer bno;

    private String name;
    private Integer price;

    @JoinColumn (name = "cno")
    @ManyToOne 
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "productsEntity", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<ReviewsEntity> reviewsList = new ArrayList<>();
}
