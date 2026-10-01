package example.test3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/session")
public class SessionController {
    @GetMapping ("/add")
    public String add(@RequestParam("data") String data, HttpSession session){
        try{
        List<String> list = (List<String>) session.getAttribute("dataList");
        if(list==null) list = new ArrayList<>();
        list.add(data);
        session.setAttribute("dataList", list);
        return "세선저장 성공";
        }catch(Exception e){return "세선저장 실패";}
    }

    @GetMapping("/all")
    public List<String> all(HttpSession session){
        List<String> result = (List<String>) session.getAttribute("dataList");
        return result;
    }

}
