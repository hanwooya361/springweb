package example.day02.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import example.day02.model.dto.BoardDto;

public class BoardDao extends BaseDao{
    private BoardDao(){}
    private static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance(){ return instance; }

     // [1] 등록 DAO
    public boolean save( BoardDto boardDto ){
        try{ 
            // 1.1 SQL 작성 , 값에 와일드카드(?) 이용한 매개변수 대입 
            String sql = "insert into board(content,writer) values( ? , ? )";
            // 1.2 연동된 데이터베이스에 SQL 기재 , 예외 필수
            PreparedStatement ps = conn.prepareStatement( sql ); // conn 멤버변수는 BaseDao에게 물려받음.
            // 1.3 기재된 SQL문법내 ?(와일드카드) 매개변수 값 대입, ps.set타입( ?순서번호 , 값 );
            ps.setString(1, boardDto.getContent() ); // 1( 첫번째 ? ) 에 dto content 대입
            ps.setString(2, boardDto.getWriter() ); // 2( 두번째 ? ) 에 dto writer 대입 
            // 1.4 기재된 SQL 실행 , .executeUpdate() insert/update/delete 에서 사용
            int result = ps.executeUpdate(); // 실행 후 처리된 레코드 수 반환 
            // 1.5 SQL 결과
            if( result == 1 ) return true; // 성공 의미 갖는 true 반환 
        }catch( SQLException e ){ System.out.println( e ); }
        // 1.5 SQL 결과 
        return false; // 실패 의미 갖는 false 반환
    } // 등록 end 

    // [2] 전체조회 DAO
    public ArrayList<BoardDto> findAll( ){
        ArrayList<BoardDto> list = new ArrayList<>(); // 2.7 레코드 정보 들을 담을 리스트
        try{
            String sql = "select * from board"; // 2.1 SQL 작성한다.
            PreparedStatement ps = conn.prepareStatement( sql ); // 2.2 SQL 기재한다. *예외발생 
            // 2.3 ?매개변수 대입한다.<생략>
            ResultSet rs =  ps.executeQuery(); // 2.4 기재된 SQL 실행 , .executeQuery() select
            // 2.5 SQL 결과( select 조회 결과는 항상 테이블로 반환한다. ) 즉] 레코드 하나씩 타입변환
            while( rs.next() ){ // rs.next() : 다음 레코드(행) 이동 , 마지막 레코드까지 하나씩 이동 반복 뜻 // 레코드 수만큼 반복
                // 2.6 현재 레코드의 필드값 들을 --> DTO 변환
                BoardDto boardDto = new BoardDto(); 
                boardDto.setNo( rs.getInt("no") ); // rs.get타입( "가져올속성명" )
                boardDto.setContent( rs.getString("content") );
                boardDto.setWriter( rs.getString("writer") );
                // 2.7 변환한 DTO --> 리스트에 담기
                list.add( boardDto );
            }
        }catch( SQLException e ){ System.out.println(e); } 
        // 2.8 리스트 반환
        return list; 
    } // 전체조회 end 
}
