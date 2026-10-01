package example.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/redis")
@RequiredArgsConstructor 
public class RedisController {
    // [1] 레디스 조작 객체(문자열 기반의 자료 레디스에 crud)
    private final StringRedisTemplate stringRedisTemplate;

    @GetMapping("/add")
    public String add(@RequestParam ("data")String data){
    stringRedisTemplate.opsForValue().set(data, data);     
    return "레디스저장성공";
}
    
    // [2] 전체조회
    @GetMapping("/all")
    public List<String> all(){
    Set<String> keys = stringRedisTemplate.keys("*");  
    List<String> list = new ArrayList<>();
    for(String key : keys){
        String data = stringRedisTemplate.opsForValue().get(key);
        list.add(data);
    }
    return list;
}
}
