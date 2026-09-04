package example.Mini2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class MemberService {
    @Autowired private MemberRepository memberRepository;
    // 회원가입
    public boolean mAdd(MemberCreateDto memberDto){
        MemberEntity memberEntity = memberDto.toEntity();
        MemberEntity savedEntity = memberRepository.save(memberEntity);
        if(savedEntity.getMember_id() >= 1){return true;}
        return false;
    }
    // 회원 전체조회
    public List<MemberCreateDto> mView(){
        List<MemberEntity> entities = memberRepository.findAll();
        List<MemberCreateDto> list = new ArrayList<>();
        entities.forEach( (entity) -> {
            MemberCreateDto dto = MemberCreateDto.from(entity);
            list.add(dto);
        });
        return list;
    }

    // 수정
    @Transactional
    public boolean Update( MemberUpdateDto memberDto ) {
        MemberEntity memberEntity = memberDto.toEntity();
        Optional<MemberEntity> optional = memberRepository.findById(memberDto.getMember_id());
        if(optional.isPresent()) {
            MemberEntity savedEntity = optional.get();
            savedEntity.setCreated_at(memberEntity.getCreated_at());
            savedEntity.setMember_type(memberEntity.getMember_type());
            savedEntity.setName(memberEntity.getName());
            savedEntity.setPassword(memberEntity.getPassword());
            savedEntity.setPhone(memberEntity.getPhone());
            return true;
        }
        return false;
    }
    // [4] 삭제
        public boolean memberDelete( int memberId ){
            Optional<MemberEntity>optional =
            memberRepository.findById(memberId);
            if(optional.isPresent() ){
                memberRepository.delete(optional.get());
                return true;
            }
            return false;
        }

}
