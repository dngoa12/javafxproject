package myPage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bookmanagement.Book;
import bookmanagement.InitController;
import bookmanagement.User;
import dao.DAO;
import dao.DAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.AdminService;
import service.AdminServiceImpl;
import service.CommonService;
import service.CommonServiceImpl;


public class MypageController extends InitController implements Initializable{

	private static final String id = null;
	private Parent root;
	private CommonService cs;
	private AdminService as;
	private DAO dao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cs = new CommonServiceImpl();
		as = new AdminServiceImpl();
		dao = new DAOImpl();
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}

	public void logoutProc(ActionEvent event) {
		cs.logout(event);
	}

	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
	}

	public void mypage1() {
		// mypage0 -> 회원정보확인 전 id pw 확인
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../myPage/mypage1.fxml");
	}

	public void mypage2() {
		// mypage1 -> 회원정보확인
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../myPage/mypage2.fxml");
	}

	public void mypage3() {
		// mypage2 -> 회원정보수정
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../myPage/mypage3.fxml");
	}

	public void mypage4() {
		// mypage3-> 회원정보 id pw 입력후 탈퇴
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../myPage/myPage4.fxml");
	}

	public void mypageLogin() {
		TextField idCheck = (TextField) root.lookup("#idCheck");
		PasswordField pwCheck = (PasswordField) root.lookup("#pwCheck");

		String id = idCheck.getText();
		String pw = pwCheck.getText();

		dao.mypageLogin(id, pw);
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../myPage/myPage2.fxml");
	}


	public void mypageState(String id, String pw) {

		User u = dao.mypageLogin(id,pw);
	
		Label uName = (Label) root.lookup("#myname");
		Label uId = (Label) root.lookup("#myid");
		Label uPw = (Label) root.lookup("#mypw");
		Label uBirth = (Label) root.lookup("#mybirth");
		Label uEmail = (Label) root.lookup("#myemail");

		uName.setText(u.getName());
		uId.setText(u.getId());
		uPw.setText(u.getPw());
		uBirth.setText(u.getBirth());
		uEmail.setText(u.getEmail());
	}


	public void modifyUser() {
		User u = new User();
		TextField ID = (TextField) root.lookup("#ID");
		TextField modName = (TextField) root.lookup("#modName");
		PasswordField modPw = (PasswordField) root.lookup("#modPw");
		PasswordField modPwok = (PasswordField) root.lookup("#modPwok");
		TextField modBirth = (TextField) root.lookup("#modBirth");
		TextField modEmail = (TextField) root.lookup("#modEmail");

		String[] txtEmpty = {ID.getText(), modName.getText(), modPw.getText(), modPwok.getText(),
				modBirth.getText(), modEmail.getText()};

		String[] txtEmptyName = {"기존아이디", "이름", "비밀번호", "비밀번호확인", "생년월일", "이메일"};

		for (int i=0;i<txtEmpty.length;i++) {
			if (cs.isEmpty(txtEmpty[i])) {
				cs.errorBox("오류", "오류발견", txtEmptyName[i]+"창이 비었음");
				return;
			}
		}

		if (modPw.getText().equals(modPwok.getText())) {
			cs.errorBox("비밀번호수정", "비밀번호확인과 일치", "비밀번호 수정 가능합니다.");
		} else {
			cs.errorBox("비밀번호수정", "비밀번호확인과 불일치", "비밀번호 수정 불가능합니다.");
		}

		u.setId(ID.getText());
		u.setName(modName.getText());
		u.setPw(modPw.getText());
		u.setBirth(modBirth.getText());
		u.setEmail(modEmail.getText());

		if (dao.mypageModify(u)) {
			cs.errorBox("수정", "수정성공", "회원정보가 수정되었습니다.");

		} else {
			cs.errorBox("수정", "수정실패", "db 문제 발생");
			return;
		}

	}

	public void deleteUser() {

		TextField delId = (TextField) root.lookup("#delId");
		TextField delPw = (PasswordField) root.lookup("#delPw");

		String id = delId.getText();
		String pw = delPw.getText();

		if (dao.deleteUser(id, pw)) {
			cs.errorBox("탈퇴", "탈퇴성공", "탈퇴되었습니다.");
			Stage s = (Stage) root.getScene().getWindow();
			cs.showWindow(s, "../bookmanagement/login.fxml");
		} else {
			cs.errorBox("탈퇴", "탈퇴실패","다시 시도해주세요.");
		}

	}

}



