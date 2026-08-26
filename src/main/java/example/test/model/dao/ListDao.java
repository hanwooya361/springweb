package example.test.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import example.test.model.dto.ListDto;

public class ListDao extends BaseDao{
    private ListDao(){}
    private static final ListDao instance = new ListDao();
    public static ListDao getInstance(){ return instance; }

    // [1] 등록 
    public boolean save( ListDto listDto ){
        try{// 1. SQL 작성
            String sql = "insert into list( pn, num ) values( ? , ? )";
            // 2. SQL 기재( SQL은 자바가 아니고 외부(MYSQL서버) 전달 )
            PreparedStatement ps = conn.prepareStatement( sql );
            // 3. 기재된 SQL에 매개변수 대입 , ps.set타입( ?순서번호 , 입력받은값 )
            ps.setString( 1 , listDto.getPn() );
            ps.setInt( 2 , listDto.getNum() );
            // 4. 기재된 SQL 실행, ps.execute() 단순실행 , .executeUpdate() 실행후반영된레코드수반환
            int result = ps.executeUpdate();
            // 5. SQL 실행 결과
            if( result == 1 ){ return true; } // 만약에 레코드 등록이 1개 되었다면 성공 true
        }catch(Exception e ){ System.out.println( e ); }
        return false; // 등록 실패시 false 
    }

    public ArrayList<ListDto> findAll( ){
        ArrayList<ListDto> list = new ArrayList<>(); // 2.7 레코드 정보 들을 담을 리스트
        try{
            String sql = "select * from list"; // 2.1 SQL 작성한다.
            PreparedStatement ps = conn.prepareStatement( sql ); // 2.2 SQL 기재한다. *예외발생 
            // 2.3 ?매개변수 대입한다.<생략>
            ResultSet rs =  ps.executeQuery(); // 2.4 기재된 SQL 실행 , .executeQuery() select
            // 2.5 SQL 결과( select 조회 결과는 항상 테이블로 반환한다. ) 즉] 레코드 하나씩 타입변환
            while( rs.next() ){ // rs.next() : 다음 레코드(행) 이동 , 마지막 레코드까지 하나씩 이동 반복 뜻 // 레코드 수만큼 반복
                // 2.6 현재 레코드의 필드값 들을 --> DTO 변환
                ListDto listDto = new ListDto(); 
                listDto.setNo(rs.getInt("no"));
                listDto.setPn( rs.getString("pn") ); // rs.get타입( "가져올속성명" )
                listDto.setNum( rs.getInt("num") );
                // 2.7 변환한 DTO --> 리스트에 담기
                list.add( listDto );
            }
        }catch( SQLException e ){ System.out.println(e); } 
        // 2.8 리스트 반환
        return list; 
    } // 전체조회 end 

    public boolean update( ListDto listdDto ){
        try{
            String sql = "update list set num = ? where pn = ? ";// 1.1 SQL 작성
            PreparedStatement ps = conn.prepareStatement(sql); // 1.2 SQL 기재 *예외*
            ps.setInt( 1 , listdDto.getNum() );// 1.3 SQL내 ? 매개변수대입
            ps.setString( 2 , listdDto.getPn() );
            int result = ps.executeUpdate(); // 1.4 SQL 실행
            if( result == 1 ) return true; // 1.5 실행 결과 반환
        }catch( SQLException e ){ System.out.println( e ); }
        return false; // 1.5 실행 결과 반환
    }

    // [4] 개별삭제 DAO 
    public boolean delete( ListDto listdDto ){
        try{ String sql = "delete from list where pn = ?";
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setString( 1 , listdDto.getPn() ); // SQL 문법내 첫번째 ? 에 매개변수 값 대입 
            int result = ps.executeUpdate();
            if( result == 1 ) return true;
        }catch( SQLException e ){ System.out.println( e ); }
        return false;
    }
}
