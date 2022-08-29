package login;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao3.DAO;
import dao3.DAOImpl;
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
import service3.CommonService;
import service3.CommonServiceImpl;

public class MyPageController extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	private DAO dao;
	private String id5;
	
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	@FXML TableColumn<Rental, String> myRno;
	@FXML TableColumn<Rental, String> myBno;
	@FXML TableColumn<Rental, String> myOut_date;
	@FXML TableColumn<Rental, String> myDue_date;
	@FXML TableColumn<Rental, String> myIn_date;
	@FXML TableColumn<Rental, String> myYn;
	@FXML Button bannap;
	
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
		cs.showWindow(s, "../userMain/Mainform.fxml", id5);
	}
	
	public void myRentalSearchProc() {
		
		List<Rental> list = dao.myRental(id5);
		
		TableView<Rental> myList = (TableView<Rental>) root.lookup("#myPageList");
		
		myRno.setCellValueFactory(new PropertyValueFactory<Rental, String>("rno"));
		myBno.setCellValueFactory(new PropertyValueFactory<Rental, String>("bno"));
		myOut_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("out_date"));
		myIn_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("in_date"));
		myDue_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("due_date"));
		myYn.setCellValueFactory(new PropertyValueFactory<Rental, String>("Yn"));
		
		myList.getColumns().setAll(myRno, myBno, myOut_date, myIn_date, myDue_date, myYn);
		
		ObservableList<Rental> oList = FXCollections.observableArrayList(list);
		
		myList.setItems(oList);
		
		bannap.setOnAction(e -> {
			if (myList.getSelectionModel().isEmpty()) {
				cs.errorBox("", "반납할 도서가 선택되지 않음", "");
				return;
			} else {
				
				
				int selectedBno = myList.getSelectionModel().getSelectedItem().getBno();
				int selectedRno = myList.getSelectionModel().getSelectedItem().getRno();
				
				if (dao.modifyBannap(selectedRno, id5)) {
					cs.errorBox("", "반납이 정상적으로 이루어짐", "");
					myList.getItems().clear();
					
					List<Rental> bannapList = dao.myRental(id5);
					
					myRno.setCellValueFactory(new PropertyValueFactory<Rental, String>("rno"));
					myBno.setCellValueFactory(new PropertyValueFactory<Rental, String>("bno"));
					myOut_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("out_date"));
					myIn_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("in_date"));
					myDue_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("due_date"));
					myYn.setCellValueFactory(new PropertyValueFactory<Rental, String>("Yn"));
					
					myList.getColumns().setAll(myRno, myBno, myOut_date, myIn_date, myDue_date, myYn);
					
					ObservableList<Rental> bannapoList = FXCollections.observableArrayList(bannapList);
					
					myList.setItems(bannapoList);
				} else {
					cs.errorBox("", "db수정중 문제발생", "");
				}
				
				
				
				
				
			}
		});
		
		
		
	}
}
