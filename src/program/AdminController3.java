package program;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao2.DAO;
import dao2.DAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service2.CommonService;
import service2.CommonServiceImpl;

public class AdminController3 extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	private DAO dao;
	private String id5;
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	
	@FXML TableColumn<User, String> name;
	@FXML TableColumn<User, String> id;
	@FXML TableColumn<User, String> pw;
	@FXML TableColumn<User, String> email;
	@FXML TableColumn<User, String> birth;
	
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
	
	public void admin1() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin1.fxml", id5);
	}
	
	public void admin4() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin4.fxml", id5);
	}
	
	public void admin5() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin5.fxml", id5);
	}
	
	public void showUserColumn() {
		List<User> l = dao.allUser();
		TableView<User> userList = (TableView<User>) root.lookup("#userList");
		
		
		name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		pw.setCellValueFactory(new PropertyValueFactory<User, String>("pw"));
		email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		birth.setCellValueFactory(new PropertyValueFactory<User, String>("birth"));
		
		userList.getColumns().setAll(name, id, pw, email, birth);
		
		ObservableList<User> oList = FXCollections.observableArrayList(l);
		userList.setItems(oList);
		
	}

}
