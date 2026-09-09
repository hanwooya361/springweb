package example.day07;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class Exam1 {
    public static void main(String[] args) {
        // 서로다른 클래스의 메소드 호출하는 방법
        // 메소드란? 상호작용(2개이상의 개체 주고받는)
        // 1. 인스턴스 생성해 메소드 호출(인스턴스 o)
        TestService testService = new TestService();
        int result1 = testService.plus(3, 10);

        // 2. 싱글톤(인스턴스 o)
        /* TestService testService2 = TestService.getInstance();
        int result2 = testService2.plus(3, 5); */

        // 3. 메소드가 static이면(인스턴스 x)
        int result3 = TestService.plus2(3, 3);
        
        // 4. 스프링방식: @Service(자동인스턴스생성)(인스턴스 o)
        /* @Autowired  private TestService testService;
        int result4 = testService.plus(1, 2); */

        // 인스턴스(주체) vs static(주체x)
        // 메소드 안에서 주체가 필요없으면 static 가능, 주체가 필요한 경우 인스턴스 생성
        // memberDto.toEntity() <-- 현재 dto 인스턴스가 엔티티로
        // MemberDto.from(entity); <-- 변활할 엔티티를 매개변수로 전달

        // 5. 사칙연산(연산은 하나의 값만 반환)
        int x = 10+2+5;

        TestService t3 = new TestService();
        t3.개별호출().밥먹기(); // 강호동
    }
}

@Service 
class TestService{

    /* private TestService(){}
    private static final TestService instance = new TestService();
    public static TestService getInstance(){return instance;} */

    int plus(int x, int y){
        return x+y;
    }

    static int plus2(int x, int y){
        return x+y;
    }

    List<Student> list = new ArrayList<>();
    TestService(){
        list.add(new Student("유재석"));
        list.add(new Student("강호동"));
    }
    Student 개별호출(){return list.get(1);}
}
class Student{
    String name;
    void 밥먹기(){
        System.out.println(this.name+"밥먹는다");
    }
    Student(String name){this.name=name;}
}