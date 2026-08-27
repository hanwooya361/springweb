console.log("index.js 열림")
	
// [1] 전체조회 , function 함수명( 매개변수명 ){ } 
async function boardFindAll( ){
    console.log( "boardFindAall 열림");
    // 1. 어디에: html table 본문에 , 식별 , .클래스명 vs #ID명 
        // document(HTML문서).query(질의/요청)Selector(선택자)
    let tbody = document.querySelector('.boardList');  
    // 2. 무엇을 ( HTTP 통신(AXIOS) 이용한 백엔드에게 요청 )
    // * await axios.HTTP메소드명( "HTTP주소" ) *현재 함수명 앞에 async 작성
    // * 동기화 하는 이유: 해당 통신 이후에 아래 코드 실행 하기 위해
    // * 비동기화( 요청 후 응답 대기없음 ) , 동기화( 요청 후 응답 대기 )
    let html = "";                     
    const 응답결과 = await axios.get( "http://127.0.0.1:8080/board/findall" );
    // { header:~~ , data:~~ , config: ~~ } // data:통신결과데이터  
    const 게시물리스트 = 응답결과.data;
        for( let index = 0 ; index <= 게시물리스트.length-1 ; index++ ){
            const 게시물객체 = 게시물리스트[ index ]; 
            // `백틱이란? 문자열과 문자열 사이에 ${} 이용하여 변수 대입가능
            html += `<tr>
                    <td> ${ 게시물객체.no } </td> <td> ${ 게시물객체.writer } </td> 
                    <td> ${ 게시물객체.content } </td> 
                    <td> <button>수정</button> <button>삭제</button> </td> 
                    </tr>`
        } // for end 
    // 3. 출력 , <마크업> inner </마크업>   
    tbody.innerHTML = html;
} // f end 
boardFindAll(); // * HTML(JS포함) 열릴 때 최초1번실행
