package program;

import java.net.URL;
import java.util.ResourceBundle;

import dao2.DAO;
import dao2.DAOImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import service2.CommonService;
import service2.CommonServiceImpl;

public class AdminController4 extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	private DAO dao;
	
	private String id5;
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void logoutProc() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/adminLogin.fxml", id5);
	}
	
	public void admin2() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin2.fxml", id5);
	}
	
	public void admin3() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin3.fxml", id5);
	}
	
	public void admin1() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin1.fxml", id5);
	}
	
	public void admin5() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin5.fxml", id5);
	}

}
