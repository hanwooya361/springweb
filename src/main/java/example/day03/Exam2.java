package example.day03;

import lombok.NoArgsConstructor;

public class Exam2 {
    public static void main(String[] args) {
        
    }
}

@NoArgsConstructor  // 매개변수 없는 생성자 (자동)생성
@AllArgsConstructor // 전체 매개변수 있는 생성자 (자동)생성
// @RequiredArgsConstructor    // final 멤버변수의 생성자 (자동)생성
@Getter @Setter
class Student{
    // 1. 멤버변수
    private String name; // 이름
    private int kor;     // 국어점수`
    private int math;    // 수학점수`
    // 2. 생성자
}
