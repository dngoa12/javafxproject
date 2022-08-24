package Login.service;

import Login.User;


import Login.DAO.UserDAO;
import Login.DAO.UserDAOImpl;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService{
	UserDAO dao;
	CommonService comServ;
	public LoginServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new UserDAOImpl();
		comServ = new CommonServiceImpl();
	}
	
	@Override
	public void LoginProc(Parent root) {
		// TODO Auto-generated method stub
		TextField idTxt = (TextField) root.lookup("#txtid");
		PasswordField pwTxt = (PasswordField) root.lookup("#txtpw");
		
		String id = idTxt.getText();
		String pw = pwTxt.getText();
		if(dao.checkLogin(id, pw)) {
			comServ.errorBox("로그인", "로그인 여부", "로그인에 성공했습니다.");
		} else {
			comServ.errorBox("로그인", "로그인 여부", "로그인에 실패했습니다.");
		}
		
		
	}

	
}