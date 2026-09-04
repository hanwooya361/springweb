package example.Mini2;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Table(name="member")
@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class MemberEntity {
    @Id 
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer member_id;
    @Column(nullable = false, length = 30 , unique = true)
    private String login_id;
    @Column(nullable = false, length = 30)
    private String password;
    @Column(nullable = false, length = 30 , unique = true)
    private String phone;
    @Column(nullable = false, length = 30)
    private String member_type;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(columnDefinition = "DATE")
    private String created_at;

    @OneToMany(mappedBy = "memberEntity")
    @ToString.Exclude
    @Builder.Default
    private List<DonationEntity> donationList = new ArrayList<>();
    
}
