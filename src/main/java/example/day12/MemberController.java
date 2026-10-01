package example.day12;

import example.day12.JwtUtil;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
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
    private final JwtUtil jwtUtil;
    private final MemberService memberService;
    private final JwtUtil jwtutil;
    // [1] 회원가입
    @PostMapping("/signup")
    public boolean signup(@RequestBody MemberDto memberDto){
        return memberService.signup(memberDto);
    }

    private final RedisTokenService redisTokenService;

    // [2] 로그인
    @PostMapping("/login")
    public MemberDto login(@RequestBody MemberDto memberDto, HttpServletResponse response){
        // 1. 서비스에게 인증/로그인 확인 (기존유지)
        MemberDto result = memberService.login(memberDto);
        if(result==null)return null;    // 로그인 실패시
        // 쿠키는 세션과 다르게 클라이언트에 저장되므로 회원번호만 저장(민감한 개인정보같은 정보들은 넣지 말자)
        // ResponseCookie cookie = ResponseCookie.from("쿠키명", "쿠키값").build();
        // *참고: 정수 -> 문자 타입변환 방법1) 정수+"" , 방법2) String.valueOf(정수)    ,   쿠키값은 String 타입임
        // 4. ********* 토큰 2개 발급 요청
        String accessToken = jwtutil.createAccessToken(result.getMno());
        String refreshToken = jwtutil.createRefreshToken(result.getMno());
        // 5. refreshToken만 레디스에 저장
        redisTokenService.setRefreshToken(result.getMno(), refreshToken);
        // 2. 로그인 성공 시 쿠키 2개 생성/발급, 쿠키만료기간 == 토큰만료기간 동일권장
        ResponseCookie cookie1 = ResponseCookie.from("accessToken", accessToken)
                                .path("/").maxAge(Duration.ofMinutes(30))
                                .httpOnly(true).secure(false).sameSite("Lax").build();
        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", refreshToken)
                                .path("/").maxAge(Duration.ofDays(7))
                                .httpOnly(true).secure(false).sameSite("Lax").build();
        // 3. 응답 헤더에 쿠키 2개 등록, reponse.setHeader()
        response.addHeader(HttpHeaders.SET_COOKIE, cookie1.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, cookie2.toString());
        return result;
    }  

    // [3] 내 정보 조회 + 쿠키
    @GetMapping("/me")
    public MemberDto getMyInfo(@CookieValue(value = "accessToken", required = false)String token){
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
    public boolean logout(@CookieValue(value = "accessToken", required = false)String accessToken, HttpServletResponse response ){
        // 1. 만약 accessToken 존재하면 회원번호 조회 
        if(accessToken!=null){  
            Long mno = jwtUtil.getMnoFromToken(accessToken);
            // 2. 만약 회원번호 조회되면 레디스내 refreshToken 삭제하기
            redisTokenService.deleteRefreshToken(mno);
        }
        // 3. 쿠키 2개 삭제
        ResponseCookie cookie1 = ResponseCookie.from("accessToken", "")
                                .path("/").maxAge(0).httpOnly(true).secure(false).build();
        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", "")
                                .path("/").maxAge(0).httpOnly(true).secure(false).build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie1.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, cookie2.toString());
        return true;
    }

    // [5] access 토큰이 만료될 때 refresh 검증 후 토큰 재발급
    @PostMapping("/reissue")
    public MemberDto reissue(@CookieValue (value = "refreshToken", required = false)String refreshToken, HttpServletResponse response){
        // 1. refresh 토큰 가져온다. 존재 여부 확인
        if(refreshToken==null) return null;
        // 2. refresh 토큰 검증
        Long mno = jwtutil.getMnoFromToken(refreshToken);
        // 3. 레디스에 저장된 refresh 토큰 꺼내기
        String savedRefreshToken = redisTokenService.getRefreshToken(mno);
        // 4. 만약 레디스에 없거나 전달받은 토큰과 다르면 / 문제 발생
        if(savedRefreshToken==null || !refreshToken.equals(savedRefreshToken)){
            redisTokenService.deleteRefreshToken(mno); // 다르면 토큰 삭제 자동 로그아웃
            return null;
        }
        // 5. 새로운 accessToken가 refreshToken 재발급 (기존 토큰 무효화)
        String newAccessToken = jwtutil.createAccessToken(mno);
        String newRefreshToken = jwtutil.createRefreshToken(mno);
        // 6. 레디스에 새로운 refreshToken 저장
        redisTokenService.setRefreshToken(mno, refreshToken);
        // 7. 쿠키 설정
        ResponseCookie cookie1 = ResponseCookie.from("accessToken", newAccessToken)
                                .path("/").maxAge(Duration.ofMinutes(30))
                                .httpOnly(true).secure(false).sameSite("Lax").build();
        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", newRefreshToken)
                                .path("/").maxAge(Duration.ofDays(7))
                                .httpOnly(true).secure(false).sameSite("Lax").build();
        // 9. header에 2개 이상 쿠키 포함한 경우 .addHeader()
        response.addHeader(HttpHeaders.SET_COOKIE, cookie1.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, cookie2.toString());
        return memberService.getMyInfo(mno);    // 9. 토큰 재발급/회원정보 반환
        
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
