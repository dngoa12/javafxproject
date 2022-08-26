package program;

import java.net.URL;
import java.util.ResourceBundle;

import dao2.DAO;
import dao2.DAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service2.CommonService;
import service2.CommonServiceImpl;

public class LoginAdminController extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	private DAO dao;
	private String id5;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
		
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}

	public void changeUserProc() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/userLogin.fxml", id5);
	}
	
	public void adminLoginProc() {

		TextField userLoginId = (TextField) root.lookup("#loginAdminId");
		PasswordField userLoginPw = (PasswordField) root.lookup("#loginAdminPw");
		
		String id = userLoginId.getText();
		String pw = userLoginPw.getText();
		
		if (dao.checkAdminLogin(id, pw)) {
			cs.errorBox("로그인", "성공", "로그인 성공");
			Stage s = (Stage) root.getScene().getWindow();
			cs.showWindow(s, "../program/admin1.fxml", id5);
		} else {
			cs.errorBox("로그인", "실패", "로그인 실패");
		}
	}

	public void adminJoinProc() {
		Stage s = new Stage();
		cs.showWindow(s, "../program/adminJoin.fxml", id5);
	}

	public void exitProc(ActionEvent event) {
		cs.windowClose(event);
	}

	
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
}
