package example.day08;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
public class ApiService {
    // 서비스키 안전하게 application.properties에서 관리, 즉] 프로젝트간 api키는 github x
    // @Value("${application.properties속성명}")
    @Value("${api.public-data.service-key}")
    private String serviceKey;
    // 2. WebClient 객체 빌더패턴 생성
    WebClient webClient = WebClient.builder().build();
    // [1]. 대구광역시 중구맛집 현황(json)
    public Map<String,Object> test1(){
        // 1. API 주소(공공데이터 신청한 API 요청 URL)
        String url = "https://api.odcloud.kr/api/15052602/v1/uddi:855807e2-fe8a-4e47-8a5a-ce1894e410d7_201909031553";
        url += "?page="+1;
        url += "&perPage="+10;
        url += "&serviceKey="+serviceKey;
        // 3. WebClient 객체 이용한 api 요청 하고 응답받기
        Map<String,Object> response = webClient.get()     // .http메소드명 http GET메소드
            .uri(url)       // uri는 http 주소상에 자원(쿼리스트링)까지 포함
            .retrieve()     // 요청 결과 반환 결과 수신
            .bodyToMono(Map.class)   // 응답결과 content-type 직렬화/변환, json -> map
            .block();       // 동기화
            return response;
        }
        
    // [2]. 국립중앙의료원 전국 약국 정보 조회 시스템, xml
    public Map<String,Object> test2(){
        // 1. API 주소(공공데이터 신청한 API 요청 URL)
        String url = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
        url += "?serviceKey="+serviceKey;
        url += "&pageNo="+1;
        url += "&numOfRows"+10;
        // 3. 주의할점: webClient에서 xml 타입을 String 타입으로 가져오기
        String reponse = webClient.get().uri(url).retrieve()
                .bodyToMono(String.class)  // XML -> Map 직렬화/변환 실패
                .block();
        // 4. String -> xml 타입 변환, 
        XmlMapper xmlMapper = new XmlMapper();  // xml매퍼 객체 생성
        // Map<String,Object> map = xmlMapper.readValue(xml문자열, 타입명.class);
        try{
        Map<String,Object> map = xmlMapper.readValue(reponse, Map.class); 
        return map;
        }catch(Exception e){System.out.println(e);}
        return null;
    }

    // [3]. 프로젝트내 resources>static> 파일명.csv
    public List<Map<String,Object>> test3(){
        List<Map<String,Object>> list = new ArrayList<>();  
        // 1. .csv파일경로, resources 이하 폴더
        String fileName = "static/중소벤처기업부_벤처기업명단_20260521.csv";
        // 2. ClassPathResource객체 이용해 해당 경로내 파일 가져오기 [파일객체] 
        ClassPathResource resource = new ClassPathResource(fileName);
        try{
            // 3. (대용량)파일 바이트로 읽어오기 .getInputStream().readAllBytes();
            byte[] bytes = resource.getInputStream().readAllBytes();
        // 4. 한글 인코딩, EUC-KR, CP949, UTF-8 등등
            InputStreamReader reader = new InputStreamReader(new java.io.ByteArrayInputStream(bytes), Charset.forName("CP949"));
        // 5. OpenCSV 이용해 바이트들을 대입
        CSVReader csvReader = new CSVReaderBuilder(reader).build();
        // 6. 주로 첫행은 제목(행) 가져오기 (key/속성명 사용할 예정)
        String[] headers = csvReader.readNext();    // 한줄 읽어오기
        // 7. 나머지 행들은 반복문 이용해 가져오기
        String[] values;
        while (true) {  // 읽어온 행의 값이 없을때까지
            // 8.
            values = csvReader.readNext();  // 한줄읽어오기
            if(values==null) break; // 만약 읽어온 데이터가 없으면 반복문 종료
            // 9. 반복을 이용해 map만들기
            Map<String,Object> row = new LinkedHashMap<>();
            for(int i=0; i<headers.length; i++){
                row.put(headers[i], values[i]);
            }
            // 10. list에 생성한 map 추가
            list.add(row);
        }
        }catch(Exception e){System.out.println(e);}
        return list;
    }
}
/*
    JSON VS XML VS CSV
        - JSON(자바스크립트객체): {key:value};
        - xml(마크업): <속성명>속성값</속성명>
        - csv(,쉼표구분): 값,값,값,값,값

    컬렉션프레임워크: List, Set, Map
    - List: 여러개 자료들을 인덱스로 구분해 하나의 자료에 저장
        -> [값1,값2,값3]
    - Set:  여러개 자료들을 인덱스없이 하나의 자료에 저장(중복이x)
        -> (값1,값2,값3)
    - Map:  key와value 한쌍(entry)으로 여러쌍을 하나의 자료에 저장  /json/딕셔너리 (Dto 대신, 파이썬 호환 등)
        -> {속성명:값1, 속성명:값2, 속성명:값3}

    WebClient 객체: 스프링에서 외부 API 요청 라이브러리 (의존성 설치)
        1. 설치 : start.spring.io에 reactive web에 의존성 하나 복붙 gradle에
        2. WebClient webClient = WebClient.builder().build();
    클레스명.class: 리플렉션(특정/해당 클래스 정보 반환)
*/