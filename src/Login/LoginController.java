package Login;

import java.net.URL;
import java.util.ResourceBundle;

import Login.service.CommonService;
import Login.service.CommonServiceImpl;
import Login.service.LoginService;
import Login.service.LoginServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class LoginController extends Controller implements Initializable{
	private Parent root;
	private LoginService loginServ;
	private CommonService commonServ;
	
	public void setRoot(Parent root) {
		// TODO Auto-generated constructor stub
		this.root = root;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loginServ = new LoginServiceImpl();
		commonServ = new CommonServiceImpl();
	}

	public void LoginProc() {
		loginServ.LoginProc(root);
	}
	
	public void CancelProc(ActionEvent event) {
		commonServ.WindowClose(event);
	}
	
	public void OpenMembershipForm() {
		Stage membershipForm = new Stage();
		Parent form = commonServ.showWindow(membershipForm, "../Membership.fxml");
		}
	
	
	
}