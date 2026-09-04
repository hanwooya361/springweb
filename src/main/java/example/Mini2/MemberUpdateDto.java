package example.Mini2;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter @ToString
public class MemberUpdateDto {
    private Integer member_id;
    private String login_id;
    private String password;
    private String phone;
    private String member_type;
    private String name;
    private String created_at;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
        .member_id(this.member_id)
        .login_id(this.login_id)
        .password(this.password)
        .phone(this.phone)
        .member_type(this.member_type)
        .name(this.name)
        .created_at(this.created_at)
        .build();
    }

    public static MemberUpdateDto from( MemberEntity memberEntity ) {
        return MemberUpdateDto.builder()
        .member_id( memberEntity.getMember_id())
        .login_id(memberEntity.getLogin_id())
        .password(memberEntity.getPassword())
        .phone(memberEntity.getPhone())
        .member_type(memberEntity.getMember_type())
        .name(memberEntity.getName())
        .created_at(memberEntity.getCreated_at())
        .build();
    }
}
