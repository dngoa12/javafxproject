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

public class IdCheckController extends Controller implements Initializable{
	private Parent root;
	private CommonService comServ;
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
		dao = new UserDAOImpl();
	}

	public void CancelProc(ActionEvent event) {
		comServ.WindowClose(event);
	}

	

	public void IdCheckProc(ActionEvent event) {
		// TODO Auto-generated method stub

		TextField idDouble = (TextField) root.lookup("#idCheck");
		String id = idDouble.getText();
		if(dao.idokCheck(id)) {
			comServ.errorBox("아이디중복확인", "사용 불가능","다른 아이디를 사용해주세요");
		} else {
			comServ.errorBox("아이디중복확인", "사용 가능","사용가능한 아이디입니다");
		}
		
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}
}