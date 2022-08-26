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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service2.CommonService;
import service2.CommonServiceImpl;

public class MyPageController extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	private DAO dao;
	private String id5;
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	@FXML TableColumn<Rental, String> myTitle;
	@FXML TableColumn<Rental, String> myBno;
	@FXML TableColumn<Rental, String> myOut_date;
	@FXML TableColumn<Rental, String> myDue_date;
	@FXML TableColumn<Rental, String> myIn_date;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}

	public void goUserMain() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/userMain.fxml", id5);
	}
	
	public void myRentalSearchProc() {
		
		List<Rental> list = dao.myRental(id5);
		
		TableView<Rental> myList = (TableView<Rental>) root.lookup("#myPageList");
		
		myTitle.setCellValueFactory(new PropertyValueFactory<Rental, String>("title"));
		myBno.setCellValueFactory(new PropertyValueFactory<Rental, String>("bno"));
		myOut_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("out_date"));
		myDue_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("due_date"));
		myIn_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("in_date"));
		
		myList.getColumns().setAll(myTitle, myBno, myOut_date, myDue_date, myIn_date);
		
		ObservableList<Rental> oList = FXCollections.observableArrayList(list);
		
		myList.setItems(oList);
		
		
		
		
		
	}
}
