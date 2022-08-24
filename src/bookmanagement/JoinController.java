package bookmanagement;

import java.net.URL;
import java.util.ResourceBundle;

import dao.DAO;
import dao.DAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.CommonService;
import service.CommonServiceImpl;
import service.JoinService;
import service.JoinServiceImpl;

public class JoinController extends InitController implements Initializable{

	private Parent root;
	private CommonService cs;
	private JoinService js;
	private DAO dao;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		js = new JoinServiceImpl();
		dao = new DAOImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
	}
	
	
	public void joinAdminProc() {
		// 조인어드민창에서 내용을 입력하고 빈창이 있거나 비밀번호가 다르면 다시 입력하게 하고 알맞게 입력하면 내용을 저장하는 메서드 그후 창을 끈다
		Admin a = new Admin();
		TextField tfName = (TextField) root.lookup("#name");
		TextField tfId = (TextField) root.lookup("#id");
		PasswordField pfPw = (PasswordField) root.lookup("#pw");
		PasswordField pfPwOk = (PasswordField) root.lookup("#pwOk");
		
		String[] txtEmpty = {tfName.getText(), tfId.getText(), pfPw.getText()};
		String[] txtEmptyName = {"이름", "아이디", "비밀번호"};
		
		for (int i=0;i<txtEmpty.length;i++) {
			if (cs.isEmpty(txtEmpty[i])) {
				cs.errorBox("오류", "오류발견", txtEmptyName[i]+" 입력창이 비었음");
				return;
			}
		}
		
		String pw = pfPw.getText();
		String pwOk = pfPwOk.getText();
		
		if (js.comparePw(pw, pwOk)) {
			cs.errorBox("오류", "오류발생", "암호가 다름");
			return;
		}
		
		a.setName(tfName.getText());
		a.setId(tfId.getText());
		a.setPw(pfPw.getText());
		
		if (dao.insertAdmin(a)) {
			cs.errorBox("회원가입", "성공", "정상적으로 이뤄짐");
		} else {
			cs.errorBox("회원가입", "db문제발생", "db입력중 문제발생");
			return;
		}
		
		Stage s = (Stage) root.getScene().getWindow();
		s.close();
		
		
	}
	
	public void joinUserProc() {
		// 조인유저창에서 내용을 입력하고 빈창이 있거나 비밀번호가 다르면 다시 입력하게 하고 알맞게 입력하면 내용을 저장하는 메서드 그후 창을 끈다
		User u = new User();
		TextField tfName = (TextField) root.lookup("#userName");
		TextField tfId = (TextField) root.lookup("#userId");
		PasswordField pfPw = (PasswordField) root.lookup("#userPw");
		PasswordField pfPwOk = (PasswordField) root.lookup("#userPwOk");
		
		String[] txtEmpty = {tfName.getText(), tfId.getText(), pfPw.getText()};
		String[] txtEmptyName = {"이름", "아이디", "비밀번호"};
		
		for (int i=0;i<txtEmpty.length;i++) {
			if (cs.isEmpty(txtEmpty[i])) {
				cs.errorBox("오류", "오류발견", txtEmptyName[i]+" 입력창이 비었음");
				return;
			}
		}
		
		String pw = pfPw.getText();
		String pwOk = pfPwOk.getText();
		
		if (js.comparePw(pw, pwOk)) {
			cs.errorBox("오류", "오류발생", "암호가 다름");
			return;
		}
		
		u.setName(tfName.getText());
		u.setId(tfId.getText());
		u.setPw(pfPw.getText());
		
		if (dao.insertUser(u)) {
			cs.errorBox("회원가입", "성공", "정상적으로 이뤄짐");
		} else {
			cs.errorBox("회원가입", "db문제발생", "db입력중 문제발생");
			return;
		}
		
		Stage s = (Stage) root.getScene().getWindow();
		s.close();
	}
	
	
	
}