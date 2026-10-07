package example.day14;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration      // 스프링 컨테이너 (설정컨트롤러) 빈 등록
// @Controller      // 스프링 컨테이너 (컨트롤러컨트롤러) 빈 등록
// @Service         // 스프링 컨테이너 (서비스컨트롤러) 빈 등록
// @Repository      // 스프링 컨테이너 (리포지토리컨트롤러) 빈 등록
// @RestController  // 스프링 컨테이너 (컨트롤러+ResponseBody(응답객체-자동직렬화)) 빈 등록
// @Component       // 스프링 컨테이너 (스프링 아키텍쳐 외 관계없는 클래스) 빈 등록
// ----> 스프링에서 해닫 클래스들을 확인해 빈 생성해 컨테이너 저장, 시점: @SpringBootApplication 스캔
@EnableWebSocketMessageBroker // STOMP 프로토콜 브로커 기능을 사용하는 컴포넌트 등록
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    // implements: 인터페이스 구현하겠다는 키워드 vs extends: 상속받겠다
    // 인터페이스 주역할: 추상메소드(구현안됨)들을 가지고 있는 타입, 메소드/기능 통합
    // 2. 
    @Override // 오버라이딩: 상속이면 메소드 재정의, 인터페이스면 추상메소드 구현
    public void configureMessageBroker(MessageBrokerRegistry registry){
        // 2.1: 구독(양방향 연결) 요청하는 방법/주소 정의
        // registry.enableSimpleBroker("/구독주소");
        registry.enableSimpleBroker("/sub");
        // 2.2: 구독(양방향 연결)된 상태에서 메세지 주고받는 방법/주소/엔드포인트 등록
        // registry.setApplicationDestinationPrefixes("/발행주소");
        registry.setApplicationDestinationPrefixes("/pub");
    }
    
    // 3. 
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-chat")    // 소켓 주소
                .setAllowedOriginPatterns("*"); // 모든 도메인 허용
    }
}

/*
    HTTP: 단방향통신, 무상태, 클라이언트 요청1개당 응답1개(요청없이 응답X)
        - CRUD
    WebSocket: 양방향통신, 상태유지, 한번 연결 후 연결된상태에서 양방향 통신
        - 실시간 통신(채팅, 알림)
    
    1. 웹소캣 의존성 입력 applicationproperties
    2. 브로커 설정 클래스
        - 구독 주소: ws://localhost:8080/sub        , 특정 방/경로 구독
        - 발행 주소: ws://localhost:8080/pub        , 특정 방/경로 메세지 발행
        - 소켓 주소: ws://localhost:8080/ws-chat    , 백-프 연결
    3. 
*/