package fx06_DB;

import java.net.URL;
import java.util.ResourceBundle;

import Auser.LoginService;
import Auser.LoginServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class Controller implements Initializable{
	Parent root;
	//db관련기능 만들기
	public static DBClass db; //공통적으로 쓰는 부분을 static으로 만든다
	public common.db.DBClass comDB;
	private LoginService ls;
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		db = new DBClass();
		comDB = new common.db.DBClass();
		ls = new LoginServiceImpl();
	}
	
	//회원가입 -> 사용자가 아이디 ,비번을 입력하고 회원가입 버튼을 누르면 사용자가 입력한 값을 얻어오는 기능
	public void membership() {
		TextField id = (TextField)root.lookup("#memberId");
		TextField name = (TextField)root.lookup("#memberName");
		TextField pwd = (TextField)root.lookup("#memberPwd");
		
		//저장하기
		MemberDTO dto = new MemberDTO();
		dto.setId(id.getText());    //사용자가 입력한 값 얻어오기
		dto.setPwd(pwd.getText());
		dto.setName(name.getText());
		
		//저장하는건 데이타 베이스 쪽으로 간다 : DBClass에서 작성해준다
		int result = db.insert(dto); //return값을 받아준다
		if(result==1) {
			Alert alert = new Alert(AlertType.INFORMATION);  //alert기능을 공통으로 만들면 편하다
			alert.setContentText("회원가입에 성공하셨습니다");
			alert.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("동일한 아이디가 존재합니다");
			alert.show();
		}

	}
	
	//로그인 인증
	public void login() {
		ls.loginChk(root);
		/*
		TextField id = (TextField)root.lookup("#fxId");
		TextField pwd = (TextField)root.lookup("#fxPwd");
		
		MemberDTO dto = db.loginChk(id.getText()); //사용자가 입력한 데이터와 같은 데이터를 가져온다
		//-> 로그인 인증기능 여기까지 만든뒤에 DBClass로 넘어가서 작성한다
		
		if(dto==null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("존재하지 않는 아이디 입니다");
			alert.show();
		}else {
			if(dto.getPwd().equals(pwd.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("인증성공입니다");
				alert.show();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("비밀번호 틀림");
				alert.show();
			}
		}
		*/
	}
	
}



