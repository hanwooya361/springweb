package example.Mini2;

import java.util.ArrayList;
import java.util.List;

import example.day06.CategoryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity@Table(name = "donation")
@NoArgsConstructor@AllArgsConstructor@Builder@Data
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donation_id;
    @Column(nullable = false)
    private String donation_date;   
    
    @ManyToOne  
    @JoinColumn(name="member_id") 
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "donationEntity")
    @ToString.Exclude
    @Builder.Default
    private List<BloodEntity> bloodList = new ArrayList<>();
}