package example.Practice6;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;


@Service 
public class ApiService {
    @Value ("${api.public-data.service-key}")
    private String serviceKey;   // 첫 번째 키

    @Value ("${api.public-data.service-key2}")
    private String serviceKey2;  // 두 번째 키
    WebClient webClient = WebClient.builder().build();

    public Map<String,Object> hanwoo(){
        String url ="http://opendata.anyang.go.kr:8082/openApi/3830000/getPetRegSttus";
        url +="?numOfRows="+10;
        url +="&pageNo="+1;
        url +="&serviceKey="+serviceKey;
        Map<String,Object> response = webClient.get().uri(url).retrieve().bodyToMono(Map.class).block();
        return response;
    }

    public Map<String, Object> api4() {
        String url = "https://api.odcloud.kr/api/15111326/v1/uddi:186ae4bf-c35e-4c8e-9122-9d1d12946c8c";
        url += "?page=" + 1;
        url += "&perPage=" + 10;
        url += "&serviceKey=" + serviceKey;

        Map<String, Object> response = webClient.get()
                .uri(URI.create(url)) // 주소를 지정해야 요청이 나간다.
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        return response;
    }

     public Map<String,Object> test4(){
        // 1. API 주소( 공공데이터 신청한 api 요청 url )
        String url ="https://api.odcloud.kr/api/15073993/v1/uddi:121cbcae-2af5-4827-9b4a-f9b6ad401f62";
        url += "?page="+1;
        url += "&perpage="+10;   
        url += "&serviceKey="+serviceKey;
        // 3. webClient 객체 이용한 api 요청 하고 응답받기
        Map<String,Object> response = webClient.get() // .http메소드명 http GET메소드
                .uri(url) // uri는 http 주소상에 자원(쿼리스트링)까지 포함
                .retrieve() // 요청 결과 반환 결과 수신
                .bodyToMono( Map.class ) // 응답 결과 content.type 직렬화/변환
                .block(); // 동기화
                return response;
        // 응답이 JSON이라서 bodyToMono(Map.class)로 바로 변환 가능
    }

     public Map<String,Object> test1(){
        // 1. API 주소( 공공데이터 신청한 api 요청 url )
        String url = "https://apis.data.go.kr/1220000/retrieveTrifFxrtInfo/getRetrieveTrifFxrtInfo";
        url += "?serviceKey=" + serviceKey2;       // 1. 첫 번째는 ?와 인증키
        url += "&aplyBgnDt=" + "20150101";        // 2. 조회 시작일
        url += "&weekFxrtTpcd=" + "2";            // 3. 주간 환율 구분 코드
        
        String response = webClient.get( ).uri( url ).retrieve()
                .bodyToMono(String.class) // XML 타입 --String타입 
                .block();
        // 4. String타입 -> xml 타입 변환 , 
        XmlMapper xmlMapper = new XmlMapper(); // xml매퍼 객체 생성
        // Map<String,Object> map = xmlMapper.readValue( xml문자열 , 타입명.class ); // +일반예외
        try{
            Map<String,Object> map = xmlMapper.readValue( response , Map.class );
            return  map;
        }catch( Exception e ){ System.out.println( e ); }
        return  null;
    }
}


