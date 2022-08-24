package bookmanagement;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import service.CommonService;
import service.CommonServiceImpl;
import service.LoginService;
import service.LoginServiceImpl;

public class LoginController extends InitController implements Initializable{

	private Parent root;
	private CommonService cs;
	private LoginService ls;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		ls = new LoginServiceImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void joinProc() {
		// 기본창에서 회원가입을 누르면 실행되는 메서드로 유저가입or관리자가입을 선택하는 타입초이스창으로 넘어감
		Stage s = new Stage();
		cs.showWindow(s, "../bookmanagement/typeChoice.fxml");
	}
	
	public void joinUser() {
		// 타입초이스창에서 유저를 클릭했을 때 실행되는 메서드로 유저회원가입을 하는 조인유저창으로 넘어감
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../bookmanagement/joinUser.fxml");
	}
	
	public void joinWindow() {
		// 타입초이스창에서 관리자를 클릭했을 때 실행되는 메서드로 관리자회원가입을 하는 조인어드민창으로 넘어감
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../bookmanagement/joinAdmin.fxml");
	}
	
	public void cancelProc(ActionEvent event) {
		// 기본창과 타입초이스 창에서 뒤로혹은 종료를 눌렀을 때 창을 꺼주는 메서드
		cs.windowClose(event);
	}
	
	public void loginProc() {
		// 기본창에서 저장된 정보와 일치하는 계정을 입력했을 때 어드민창으로 넘어가는 메서드
		ls.loginProc(root);
	}
	
	public void goUserLogin() {
		Stage s = (Stage) root.getScene().getWindow();
		root = cs.showWindow(s, "../bookmanagement/login2.fxml");
	}
	
	public void goAdminLogin() {
		Stage s = (Stage) root.getScene().getWindow();
		root = cs.showWindow(s, "../bookmanagement/login.fxml");
	}
	
	public void userLoginProc() {
		ls.userLoginProc(root);
	}
}
