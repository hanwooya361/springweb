package example.day12;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor 
public class RedisTokenService {
    // [1] 레디스 조작 객체 주입
    private final StringRedisTemplate stringRedisTemplate;

    // [2] refresh token 레디스에 저장함수
    public void setRefreshToken(Long mno, String token){
        // key엔 RT:회원번호 조합   // value: refresh 토큰
        // Duration.ofDays: 만료기간 설정
        stringRedisTemplate.opsForValue().set("RT:"+mno, token, Duration.ofDays(7));
    }

    // [3] refresh token 조회 함수
    public String getRefreshToken(Long mno){
        return stringRedisTemplate.opsForValue().get("RT:"+mno);// 조회할 key 조합해 조회
    }

    // [4] refresh token 삭제 함수
    public boolean deleteRefreshToken(Long mno){
        return stringRedisTemplate.delete("RT:"+mno);   // 삭제할 key 조합해 삭제
    }

}
