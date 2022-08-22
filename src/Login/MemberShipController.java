package Login;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Login.DAO.UserDAO;
import Login.DAO.UserDAOImpl;
import Login.service.CommonService;
import Login.service.CommonServiceImpl;
import Login.service.MemberShipService;
import Login.service.MemberShipServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MemberShipController extends Controller implements Initializable{
	private Parent root;
	private CommonService comServ;
	private MemberShipService membershipServ;
	private UserDAO dao;
	private ActionEvent String;

	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comServ = new CommonServiceImpl();
		membershipServ = new MemberShipServiceImpl();
		dao = new UserDAOImpl();
	}

	public void CancelProc(ActionEvent event) {
		comServ.WindowClose(event);
	}

	public void memberShipProc() {
		User user = new User();
		TextField txtName = (TextField) root.lookup("#txtName");
		TextField txtId = (TextField) root.lookup("#txtId");
		PasswordField txtPw = (PasswordField) root.lookup("#txtPw");
		PasswordField txtPwOk = (PasswordField) root.lookup("#txtPwOk");
		TextField txtBirth = (TextField) root.lookup("#txtBirth");
		TextField txtEmail = (TextField) root.lookup("#txtEmail");

		String[] txtEmpty = {txtName.getText(), txtId.getText(), txtPw.getText(), txtBirth.getText(), txtEmail.getText()};
		String[] txtEmptyName = {"이름","아이디","암호","생년월일","이메일"};

		for(int i=0;i<txtEmpty.length;i++) {
			if(membershipServ.isEmpty(txtEmpty[i])) {
				comServ.errorBox(txtEmptyName[i] + " 입력창이 비어 있습니다");
				return;
			}
		}

		String pw = txtPw.getText();
		String pwOk = txtPwOk.getText();

		if(membershipServ.comparePw(pw, pwOk)){
			comServ.errorBox("암호가 다릅니다.");
			return;
		}

		
		user.setName(txtName.getText());
		user.setId(txtId.getText());
		user.setPw(txtPw.getText());
		user.setBirth(txtBirth.getText());
		user.setEmail(txtEmail.getText());

		if(dao.insertMember(user)) {
			comServ.errorBox("회원가입", "회원가입성공", "회원가입이 정상적으로 이루어졌습니다.");
		} else {
			comServ.errorBox("DB 에러", "DB 문제 발생", "DB 입력에 문제가 발생했습니다.");
			return;
		}

		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();

	}

	public void IdCheckProc() {
		TextField idCheck = (TextField) root.lookup("#txtid");
		String id = idCheck.getText();	
		
		if(dao.IdCheck(id)) {
			comServ.errorBox("다른 아이디를 사용해주세요");
		} else {
			comServ.errorBox("사용가능한 아이디입니다");
			return;
		}
		
	}
}		
