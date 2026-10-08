package example.day14;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor 
public class MessageController {
    // 1. 메세지 템플릿
    private final SimpMessageSendingOperations messageTemp;
    // 2. 메세지 매핑 메소드, @MessageMapping("/주소")
    @MessageMapping("/chat/message")
    public void message(MessageDto messageDto){
        // 2-1: ws://localhost:8080/ws-chat/pub/chat/message 요청시 실행되는 메소드
        //      ws도메인/ws주소/발행주소/매핑주소
        // 2-2: 내용물(body) 들을 messageDto 매핑
        // [생략] 만약에 메세지 내용 영구저장 --> DB(JPA)
        // 2-4: 입장메세지, 퇴장메세지 구분
         // 2-4 : 입장메시지 , 퇴장메시지 구분
        if( messageDto.getType().equals("ENTER") ){
            messageDto.setContent( messageDto.getSender()+"님이 입장"); // 입장 메시지
        }else if( messageDto.getType().equals("QUIT") ){
            messageDto.setContent( messageDto.getSender()+"님이 퇴장"); // 퇴장 메시지 
        }
        // 2-3: 같은 방을 구독하는 클라이언트에게 메시지 전송
        // messageTemp.convertAndSend("/보낼주소", 내용물);
        // 보낼주소: ws://localhost:8080/ws-chat/sub/chat/room/3
        // [생략] 만약 DB내 메세지 보낸사람과 같은방 조회 --> DB(JAP)
        messageTemp.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto);
    }
}

