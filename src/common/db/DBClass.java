package common.db;
//공통적으로 쓰는 기능을 만들었다
import java.sql.Connection;
import java.sql.DriverManager;

//연결만 하는 역할
public class DBClass {
	public static Connection conn;  //conn은 누구든 공통적으로 쓰기 때문에 static으로 쓰고, 이러면 어디서든지 DBClass.conn으로 쓸수있다
	public DBClass() {
		//생성자
		try {
			// 자바에서 오라클 연결하기 위한 기타 기능들을 쓸수 있게 라이브러리 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 오라클과 연결된 객체를 가져옵니다
			conn = DriverManager.getConnection(  //연결객체 가져오기
					"jdbc:oracle:thin:@210.221.253.215:1521:xe", "team222", "team222");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
