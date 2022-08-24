package service;

import bookmanagement.Admin;
import dao.DAO;
import dao.DAOImpl;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService{
	
	private DAO dao;
	private CommonService cs;

	public LoginServiceImpl() {
		dao = new DAOImpl();
		cs = new CommonServiceImpl();
	}
	
	@Override
	public void loginProc(Parent root) {
		TextField loginId = (TextField) root.lookup("#id");
		PasswordField loginPw = (PasswordField) root.lookup("#pw");
		
		String id = loginId.getText();
		String pw = loginPw.getText();
		
		if (dao.checkLogin(id, pw)) {
			cs.errorBox("로그인", "로그인성공", "로그인에 성공했습니다.");
			Stage s = (Stage) root.getScene().getWindow();
			cs.showWindow(s, "../bookmanagement/admin.fxml");
			
			
		} else {
			cs.errorBox("로그인", "로그인실패","로그인에 실패했습니다.");
		}
		
		
		
	}

	@Override
	public void userLoginProc(Parent root) {
		TextField loginId = (TextField) root.lookup("#userId");
		PasswordField loginPw = (PasswordField) root.lookup("#userPw");
		
		String id = loginId.getText();
		String pw = loginPw.getText();
		
		if (dao.checkUserLogin(id, pw)) {
			cs.errorBox("로그인", "로그인성공", "로그인에 성공했습니다.");
			Stage s = (Stage) root.getScene().getWindow();
			cs.showWindow(s, "../bookmanagement/user.fxml");
			
			
		} else {
			cs.errorBox("로그인", "로그인실패","로그인에 실패했습니다.");
		}
		
	}

	

}
