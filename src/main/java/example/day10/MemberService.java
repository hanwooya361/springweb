package example.day10;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor 
public class MemberService {
    private final MemberRepository memberRepository;
    // *** [*] 비크립트(암호화 사용) 라이브러리 객체주입
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // [1] 회원가입 = 등록 = Create = c, (!) dto -> entity -> save -> confirm
    public boolean signup(MemberDto memberDto){
        // 1. 회원가입/등록 할 정보들을 컨트롤러에게 받아
        // 2. entity 변환
        MemberEntity memberEntity = memberDto.toEntity();
        // 3. entity save
            // *** [*] 저장하기전에 평문(원본 비밀번호) --> 암호문으로 변환
            // passwordEncoder.encode("평문");
        memberEntity.setMpwd(passwordEncoder.encode(memberDto.getMpwd()));
        MemberEntity savedEntity = memberRepository.save(memberEntity);
        // 4. confiem
        if(savedEntity.getMno() >= 1)return true;
        return false;
    }

    // [2] 로그인 = 조회 = read = r
    public MemberDto login(MemberDto memberDto){
        // 1. 로그인시 입력받은 아이디/비밀번호 받는다
        // 2. 입력받은 아이디가 존재하는지 검증, findByMid 추상정의함.
        MemberEntity memberEntity = memberRepository.findByMid(memberDto.getMid());
        if(memberEntity == null) return null;   // 아이디가 존재하지 않으면 null 반환
        // 3. 존재하면 *** 평문(로그인시입력받은비밀번호)과 암호문(회원가입시입력받은비밀번호) 비교!! ***
        // passwordEncoder.matches("평문", "암호문");
        boolean 비밀번호일치 = passwordEncoder.matches(memberDto.getMpwd(), memberEntity.getMpwd());
        if(비밀번호일치 == false) return null;  // [로그인실패] 비밀번호 불일치
        // 4. entity -> dto 변환해 반환, 주로 로그인전용 loginDto 있으면 좋음 
        return MemberDto.from(memberEntity); 
    }

    // [3] 내 정보 조회(PK:회원번호)
    public MemberDto getMyInfo(Long mno){
        // 1. 컨트롤러에게 조회할 회원번호 받기
        // 2. findById 이용해 회원번호 조회
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if(optional.isPresent()){   // 3. 조회결과 존재하면
            MemberEntity memberEntity = optional.get(); // entity 꺼내기
            return MemberDto.from(memberEntity);    // dto 변환
        }
        return null;    // 4. 조회 결과 없으면 null
    }
}
