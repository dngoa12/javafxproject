package login;

import java.net.URL;
import java.util.ResourceBundle;

import dao3.DAO;
import dao3.DAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service3.CommonService;
import service3.CommonServiceImpl;
import service3.JoinService;
import service3.JoinServiceImpl;

public class JoinAdminController extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	private DAO dao;
	private JoinService js;
	
	private String id5;
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
		js = new JoinServiceImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void exitProc(ActionEvent event) {
		cs.windowClose(event);
	}
	
	public void joinProc() {
		Admin a = new Admin();
		
		TextField name = (TextField) root.lookup("#adminName");
		TextField id = (TextField) root.lookup("#adminId");
		PasswordField pw = (PasswordField) root.lookup("#adminPw");
		PasswordField pwOk = (PasswordField) root.lookup("#adminPwOk");
		
		String[] empty = {name.getText(), id.getText(), pw.getText(), pwOk.getText()};
		String[] emptyName = {"이름", "아이디", "비밀번호", "비밀번호확인"};
		
		for (int i=0;i<empty.length;i++) {
			if (cs.isEmpty(empty[i])) {
				cs.errorBox("오류", "오류발견", emptyName[i]+" 입력창이 비었음");
				return;
			}
		}
		
		String p = pw.getText();
		String po = pwOk.getText();
		
		if (!(js.comparePw(p, po))) {
			cs.errorBox("오류", "오류발생", "암호가 다름");
			return;
		}
		
		a.setName(name.getText());
		a.setId(id.getText());
		a.setPw(pw.getText());
		
		if (dao.insertAdmin(a)) {
			cs.errorBox("회원가입", "성공", "가입성공");
		} else {
			cs.errorBox("회원가입", "실패", "db문제발생");
			return;
		}
		
		Stage s = (Stage) root.getScene().getWindow();
		s.close();
		
	}

}
