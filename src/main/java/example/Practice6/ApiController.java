package example.Practice6;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@CrossOrigin("http://localhost:5173")
public class ApiController {
    private final ApiService apiService;

    // 1.
    @GetMapping ("/hanwoo")
    public Map<String,Object> hanwoo(){
        return apiService.hanwoo();
    }

    @GetMapping("/api4")
    public Map<String, Object> api4() {
        return apiService.api4();
    }

    @GetMapping ("/test4")
    public Map<String,Object> test4(){
        return apiService.test4();

    }

    @GetMapping ("/test1")
    public Map<String,Object> test1(){
        return apiService.test1();

    }
}