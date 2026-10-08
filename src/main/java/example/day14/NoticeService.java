package example.day14;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
/*
    Websocket + Stomp : (양방향, 클라이언트 <--> 서버) 채팅 구현
    SSE: (단방향, 클라이언트 <-- 서버) 알림 구현
*/
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service  
public class NoticeService {
    // 1. 연결된 클라이언트들의 정보 보관 리스트
    // * 서버가 클라이언트에게 비동기 요청 보내기 위한 http 응답 객체, 연결유지
    // * 동기/비동기: 동기화(하나의 메소드를 순차적으로 실행), 비동기(하나의 메소드를 동시실행)
    // * ArrayList() 동기화지원x, Vector 동기화지원o
    // * 동기화 필요목적: 하나의 서버가 구독과 메세지전송 동시 다발적으로 순차처리
    // CopyOnWriteArrayList() : 여러개 요청들을 동시에 접속,종료,메세지전송 동시성 제공
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    
    // 2. 클라이언트 구독 처리(SseEnitter 생성해 리스트에 저장)
    public SseEmitter subscribe(){
        SseEmitter emitter = new SseEmitter(); // 2-1: SseEmitter 객체 생성
        emitters.add(emitter);  // 2-2: 리스트에 저장

        // 2-4: 안전하게 클라이언트와 연결이 비정상이면 리스트에서 삭제
        emitter.onCompletion(()->emitters.remove(emitter));

        // 2-3: 생성된 객체를 반환
        return emitter;
    }
    
    // 3. 메세지 전송(서버가 클라이언트에게 메세지 전송)
    public void onMessage(String message){
        // 3-1: 현재 리스트에 저장된 emitter들에게 메세지 보내기
        for(SseEmitter emitter : emitters){
            // 3-2: .send(SseEmitter.event().name("구독식별").data(내용물)), 일반 예외
            try{
            emitter.send(SseEmitter.event().name("notice").data(message));
            // 3-3: 메세지 전송 실패시 실패한 emitter 리스트에 제외
            }catch(IOException e){emitters.remove(emitter);}
        } 
    }

}
