package example.day03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Exam1 {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.method1(); // 부모메소드가 아닌 오버라이드 메소드가 실행됨
        subClass.method2(); // 실행은 가능하지만 권장X

        // 1. [리플렉션] 클래스의 정보 반환
        Class<TestClass> clazz = TestClass.class;
        // 2. 특정 메소드 반환
        try{
            Method method = clazz.getMethod("method3");
            // 3. 특정 메소드의 어노테이션 확인
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            // 4. 특정 어노테이션의 속성 확인
            System.out.println(annotation.value());
            System.out.println(annotation.data());
            // 5. 동적로딩: 스프링 구조
            TestClass testClass = clazz.getDeclaredConstructor().newInstance();
            method.invoke(testClass);

        }catch(Exception e){System.out.println(e);}
        
    }
}
// [3] 어노테이션 만들기: @interface 어노테이션명{}
@Retention(RetentionPolicy.RUNTIME) // 해당 어노테이션 생명주기: 실행 중 유지
@Target(ElementType.METHOD) // 해당 어노테이션 사용처: 메소드
@interface MyAnnotation{
    String value(); // 추상메소드 형식의 어노테이션 속성
    int data() default 1;   // 어노테이션 속성, default 값(기본값)
}
// [4] 어노테이션 주입/사용: 
class TestClass{
    @MyAnnotation(value="안녕", data = 10) // @내가만든어노테이션()
    public void method3(){System.out.println("하이3");}
    @MyAnnotation(value = "안녕2")
    public void method4(){System.out.println("하이4");}
}

class SuperClass{void method1(){}}  // - 상위클래스
class SubClass extends SuperClass{  // - 하위클래스
    @Override   // [1] 어노테이션: (재정의)컴파일/실행중 해당 메소드 사용하는 방법/주석 명시
    void method1(){super.method1();}
    @Deprecated // [2] 어노테이션: (더이상 사용을 권장안함)
    void method2(){}
}
