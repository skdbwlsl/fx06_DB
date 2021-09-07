package common.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//공통기능만들기
//공통적으로 쓰는 alert를 만들었다
public class AlertClass {
	public  static void alert( String msg ) {   //메세지를 전달하면
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText( msg );     //alert창 띄운다
		alert.show();
	}
}
