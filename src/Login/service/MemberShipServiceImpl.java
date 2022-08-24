package Login.service;

import Login.DAO.UserDAO;
import Login.DAO.UserDAOImpl;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class MemberShipServiceImpl implements MemberShipService{
	UserDAO dao;
	CommonService comServ;

	public  MemberShipServiceImpl() {
		dao = new UserDAOImpl();
		comServ = new CommonServiceImpl();

	}

	@Override
	public boolean isEmpty(String str) {
		// TODO Auto-generated method stub
		if(str.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean comparePw(String pw1, String pw2) {
		// TODO Auto-generated method stub
		if(pw1.equals(pw2)) {
			return false;
		}
		return true;
	}



}