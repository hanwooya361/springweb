package example.day12;

import java.time.Duration;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/member")
@RequiredArgsConstructor 
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")  // 도메인이 다른 경우 allowCredentials 이용한 쿠키/세션 유지
public class MemberController {
    private final MemberService memberService;
    private final JwtUtil jwtutil;
    // [1] 회원가입
    @PostMapping("/signup")
    public boolean signup(@RequestBody MemberDto memberDto){
        return memberService.signup(memberDto);
    }

    // [2] 로그인+쿠키
    @PostMapping("/login")
    public MemberDto login(@RequestBody MemberDto memberDto, HttpServletResponse response){
        // 1. 서비스에게 인증/로그인 확인 (기존유지)
        MemberDto result = memberService.login(memberDto);
        if(result==null)return null;    // 로그인 실패시
        // 2. 로그인 성공 시 쿠키 생성/발급
        // 쿠키는 세션과 다르게 클라이언트에 저장되므로 회원번호만 저장(민감한 개인정보같은 정보들은 넣지 말자)
        // ResponseCookie cookie = ResponseCookie.from("쿠키명", "쿠키값").build();
        // *참고: 정수 -> 문자 타입변환 방법1) 정수+"" , 방법2) String.valueOf(정수)    ,   쿠키값은 String 타입임
        // 4. ********* 토큰 발급 요청
        String token = jwtutil.createAccessToken(result.getMno());  // mno --> jwt
        ResponseCookie cookie = ResponseCookie.from("login_member", token)
                                .path("/")                  // 쿠키 사용할경로, "/" 도메인 전체
                                .maxAge(Duration.ofDays(1)) // 쿠키 유효기간 설정
                                .httpOnly(true)         // JS이용한 탈취 방지, XSS공격
                                .secure(false)            // HTTPS에서만 사용, 개발단계: FLASE, 배포단계: TRUE
                                .sameSite("Lax")        // CSRF 공격 방어
                                .build();                         // 쿠키 생성 끝
        // 3. 응답 헤더에 쿠키 등록, reponse.setHeader()
        response.setHeader(org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString());
        return result;
    }  

    // [3] 내 정보 조회 + 쿠키
    @GetMapping("/me")
    public MemberDto getMyInfo(@CookieValue(value = "login_member", required = false)String token){
        // @CookieValue(value = "쿠키명")   요청한 브라우저의 쿠키 가져오기
        // 1. 만약 loginMno이 없다면 비로그인중
        if(token==null) return null;
        // ****** 쿠키에 저장된 token 이용해 회원번호 찾기
        Long loginMno = jwtutil.getMnoFromToken(token);
        // 2. 로그인중이면 서비스에게 회원정보 요청
        // 참고: 문자->정수 방법1) 기본타입.parse타입(문자)
        return memberService.getMyInfo(loginMno);
    }

     // [4] 로그아웃 + 쿠키
    @PostMapping ("/logout")
    public boolean logout(HttpServletResponse response ){
        // 1. 삭제할 쿠키명과 동일한 이름으로 maxAge(0)해 재발급
        ResponseCookie cookie = ResponseCookie.from("login_member"," ")
                                .path("/")  // 모든 곳에서 로그아웃 가능하도록 전체
                                .maxAge(0)  // 바로 삭제
                                .httpOnly(false)
                                .secure(false)
                                .build();
        // 2. 응답객체내 헤더에 쿠키 포함
        response.setHeader(org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString());
        return true;
    }


    @GetMapping("")
    public String test(HttpServletRequest request){
        // 1)  HttpServletRequest: HTTP 요청이 들어오면 요청 정보가 담겨 있는 객체
        System.out.println(request.getRemoteAddr());    // 요청한 클라이언트의 IP (로그/위치추적/조회수)
        System.out.println(request.getHeader("User-Agent"));    // 요청한 클라이언트 브라우저 정보
        System.out.println(request.getSession());   // 요청한 클라이언트의 세션객체 정보
        // 2) 세션객체란? 톰켓 서버내 브라우저 마다 독립적인 저장소
        // 주로: 로그인성공정보, 인증번호, 비회원제장바구니 등 일시적인 휘발성 메모리
        HttpSession session = request.getSession(); // 세션객체내 여러개 정보 저장 가능
        System.out.println(session.getId());    // 세션 식별번호
        System.out.println(session.getCreationTime());  // 세션 생성시간(자동로그아웃)
        System.out.println(session.getLastAccessedTime());  // 세션 마지막 접근 시간
        System.out.println(session.getMaxInactiveInterval());   // 세션 생명주기(기본값 30분)

        // 3) ******* 세션 정보 저장=로그인/호출=마이페이지/삭제=로그아웃 *******
        session.setAttribute("data", "사과");   // map구조 (key:value)
        // data(key) 이름으로 사과(data) 저장, 주의할점: value 타입은 object라서 타입변환 필요
        System.out.println(session.getAttribute("data"));   // key 이용한 value 호출
        session.invalidate();   // 세션 초기화
        return session.getId();
    }
}
