package Auser;

import common.alert.AlertClass;
import fx06_DB.MemberDTO;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class LoginServiceImpl implements LoginService{
	LoginDB db; //LoginDB에서 넘어와 이 코드를 작성한다
	public LoginServiceImpl() {
		db = new LoginDB();
	}
	
	public void loginChk(Parent root) {
		TextField id = (TextField)root.lookup("#fxId");
		TextField pwd = (TextField)root.lookup("#fxPwd");
		
		MemberDTO dto = db.loginChk( id.getText() );  //db쪽에 연결을 해야하고, 밑의 코드까지 작성하고 LoginDB로 넘어간다
		
		String msg = null;
		if(dto == null) {
			msg = "해당아이디는 존재하지 않습니다";
		}else {
			if( dto.getPwd().equals(pwd.getText()) ) {
				msg = "인증성공 되었습니다";
			}else {
				msg = "비밀번호가 틀렸습니다";
			}
		}
		AlertClass.alert(msg);
	}
}
