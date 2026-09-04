package example.day06;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class Exam {
    public static void main(String[] args) {
        // [1] 리터럴/상수 = 기본값
        int a = 3;  // a변수가 3 참조한다
        int b = 3;  // b변수가 3 참조한다
        // 두 변수가 참조하는 값은 총 몇개인가요?   1개
        // [2] 참조란? 어떠한 값의 위치, 인스턴스(객체) 1개당 참조 1개
        String c = new String("유재석");
        String d = new String("강호동");
        // 두 변수가 참조하는 값은 총 몇개인가요?   2개
        Test t = new Test();
        t.name = new String("유재석");
        // t 변수가 참조하는 값은 총 몇개인가요?    1개(!개), 

        // 자바 참조
        // [1] 자유 카테고리 등록
        Category c1 = new Category(1, "자유", new ArrayList<>());
        // c1 참조 몇개 하고 있나요? 1개, c1 ->Category()
        // [2] 자유 카테고리에 게시물 작성
        Board b1 = new Board(1, "제목1", c1);
        // b1 참조 몇개? 1개 , b1 -> Board -> Category, String/기본타입 제외
            // *] b1 통해 c1 알수있음? 가능, why? board에 category가 있으니까
            // *] JPA 개발자는 이 구조 -> DB 연관관계(JOIN) 사용 가능하겠다, 단방향참조(FK)
            // *] C1 통해 B1 알수있나? 불가능, why? category에 board가 없으니까 
        // [3] category에 board를 넣기
        c1.getList().add(b1);
            // c1 -> category -> list(board)
            // *] C1 통해 B1 알수있나? 가능
            // *] JPA 서로 참조 가능한 구조: 양방향참조
        System.out.println(b1);
        // DB는 단방향: 참조[FK]테이블에 PK테이블의 PK값 저장 
        // DB는 양방향: X, 참조/매핑 테이블(실무적으로 권장X)
        // 결론: JPA에서는 양방향, DB는 양방향 없다
        // 실무에서는 양방향 비권장
    }
}
@Data
@AllArgsConstructor
class Board{
    private int bno;
    private String btitle;
    private Category category;  // 참조FK
}
@Data
@AllArgsConstructor
class Category{ // 카테고리 상위테이블
    private int cno;
    private String cname;
    @ToString.Exclude   // toString 사용금지
    // toString()? Object(슈퍼)클래스의 객체 주소값 반환 함수
    private List<Board> list = new ArrayList<>();
    
}

class Test{
    String name;
}