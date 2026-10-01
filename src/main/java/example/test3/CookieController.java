package example.test3;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
@RestController 
@RequestMapping ("/api/cookie")
public class CookieController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/add")
    public String add(@RequestParam("data") String data, @CookieValue (value = "dataList", required = false)String saved,  HttpServletResponse response) throws Exception{
        List<String> list = (saved == null) ? new ArrayList<>() : objectMapper.readValue(saved, List.class);
        list.add(data);
        String json = objectMapper.writeValueAsString(list);
        ResponseCookie cookie = ResponseCookie.from("dataList", URLEncoder.encode(json, StandardCharsets.UTF_8))
                                .path("/")
                                .build();
        response.setHeader(org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString());
        return "쿠카저장 성공";
    }

    @GetMapping("/all")
    public List<String> all(@CookieValue (value = "dataList", required = false)String saved)throws Exception{
        if(saved==null){
            return Collections.emptyList();
        }
        return objectMapper.readValue(saved, List.class);
    }
}
