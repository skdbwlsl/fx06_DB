package fx06_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBClass {
	//데이터 베이스 연결은 무조건 sql에 들어있는 connection
	public Connection conn;
	public DBClass() {
		try {
			//자바에서 오라클 연결하기 위한 기타 기능들을 쓸수 있게 라이브러리 등록
			//무조건 있어야한다
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//오라클과 연결된 객체를 가져옵니다
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "dbwls", "dbwls9874");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//insert기능 만들기
	public int insert(MemberDTO dto) { //반환타입을 int로 받는다
		String sql = "insert into fx_member(id,pwd,name) values(?,?,?)";
		int result = 0 ;
		
		//쿼리문을 전송할 수 있는 전송객체 얻어오기 : try,catch 이용
		try {
			//연결된 객체(conn)을 이용해서 쿼리문(명령어) 전송할 수 있는 전송객체 얻어옴
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//쿼리문의 물음표 자리에 값을 채워넣음
			ps.setString(1, dto.getId());//1번  ?자리에 id넣겠다
			ps.setString(2, dto.getPwd());//2번 ?자리에 pwd넣겠다
			ps.setString(3, dto.getName());//3번 ?자리에 name넣겠다
			
			//전송객체를 이용해서 명령어 실행(디비에 명령어 전송)
			//결과값 : 성공1, 실패0
			//보편적으로 select를 제외한 나머지 명령어는 executeUpdate를 사용한다
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace(); //무조건 넣어주기 어떤 에러인지 알기 위해서
		}
		return result; //result값이 1이면 성공, 0이면 실패
	}
	
	//controller의 loginChk기능 만들기
	public MemberDTO loginChk(String inputId) {
		//select * from fx_member where id = '111';
		String sql = "select * from fx_member where id=?";
		MemberDTO dto = null; //해당하는 값을 돌려주기위해
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, inputId);
			//ResultSet은 배열과 비슷한 방식으로 가져온다
			//ResultSet은 select 문에는 무조건 사용한다
			
			ResultSet rs = ps.executeQuery();   //데이터베이스에 접근했을때 데이터를 가져왔다면 dto는 객체를 가지게되고, 데이터가 없다면 dto는 null값을 가진다
			if(rs.next()) { //데이터를 가지고 왔다면  //if문이 실행이 안됐다면 아래 코드 dto도 객체생성에 실패한것
				dto = new MemberDTO();  //객체를 만드어주고
				dto.setId(rs.getString("id")); //id를 가져오고
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
		//여기까지 코드를 입력하고 DBClass로 넘어간다
	}

}
