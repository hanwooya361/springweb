package example.Mini2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController 
public class MemberController {
    @Autowired private MemberService memberService;
    // 회원가입
    @PostMapping("/blood")
    public boolean mAdd(@RequestBody MemberCreateDto memberDto){
        return memberService.mAdd(memberDto);
    }
    // 조회
    @GetMapping("/blood")
    public List<MemberCreateDto> mView( ) {
        return memberService.mView();
    }
    // 수정
    @PutMapping("/blood")
    public boolean Update( @RequestBody MemberUpdateDto memberDto ){
        return memberService.Update( memberDto );
    }
    // 삭제
     @DeleteMapping("/member")
    public boolean memberDelete( @RequestParam(name = "memberId") int memberId){
        return memberService.memberDelete(memberId);
    }

}
