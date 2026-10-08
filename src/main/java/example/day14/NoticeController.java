package example.day14;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/sse")
@RequiredArgsConstructor 
@CrossOrigin(origins = "*")
public class NoticeController {
    private final NoticeService noticeService;

    // 1. 알림 구독 매핑
    // * 응답 content-type json이 아닌 스트림 타입으로 변경
    // Mediatype 자동완성시 스프링프레임워크
    @GetMapping (value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(){
        return noticeService.subscribe();
    }

    // 2. 알림 메세지 전송 * 알림 테스트 용도 *
    @GetMapping ("/message")
    public void onMessage(@RequestParam (name="msg")String msg){
        noticeService.onMessage(msg);
    }

}
