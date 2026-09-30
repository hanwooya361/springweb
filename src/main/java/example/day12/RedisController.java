package example.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/redis")
@RequiredArgsConstructor 
public class RedisController {
    // [1] 레디스 조작 객체(문자열 기반의 자료 레디스에 crud)
    private final StringRedisTemplate stringRedisTemplate;
    // 1. 
    @GetMapping("/test1")
    public Map<String, Object> test1(){
        // [2] 레디스에 자료 삽입, key:value, 문자열타입
        // key 중복 불가능, value 중복 가능 = NOSQL
        stringRedisTemplate.opsForValue().set("유재석", "90");
        stringRedisTemplate.opsForValue().set("강호동", "100");
        stringRedisTemplate.opsForValue().set("신동엽", "70");
        // [3] 레디스에 자료 조회, .keys("*"), 모든 자료들의 키 조회, Set<String> 컬렉션으로 반환
        // 참고: 컬렉션프레임워크(List, Map, Set)
        Set<String> keys = stringRedisTemplate.keys("*");
        Map<String,Object> map = new HashMap<>();
        for(String key: keys){  // 모든 키 반복 
            String data = stringRedisTemplate.opsForValue().get(key);  // 키 이용 value 호출
            map.put(key, data);
        }
        return map;
    }

    // ************* redis CRUD *************
    private final ObjectMapper objectMapper = new ObjectMapper();   // 직렬화
    @PostMapping("/member")
    public boolean save(@RequestBody MemberDto memberDto) throws JsonProcessingException{
        // 1. 중복 없는 key 구성(예] 도메인명:식별키 )
        String key = "member:"+memberDto.getMno();  // 예] member:3
        // 2. 문자열템플릿에 DTO/자바객체 대입, DTO -> 문자열(직렬화), 반대 (역직렬화)
        // .writeValueAsString(자바객체);
        String str = objectMapper.writeValueAsString(memberDto); // dto -> string 직렬화
        // 3. 레디스에 저장
        stringRedisTemplate.opsForValue().set(key, str);    // { "member:1" : {mno:1, mid:qwe} }
        return true;
    }

    
}
