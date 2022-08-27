package login;

import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service3.CommonService;
import service3.CommonServiceImpl;

public class UserController extends InitController implements Initializable{

	private Parent root;
	private CommonService cs;
	private DAO dao;
	private String id5;
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	@FXML TableColumn<Book, String> title;
	@FXML TableColumn<Book, String> bno;
	@FXML TableColumn<Book, String> author;
	@FXML TableColumn<Book, String> publisher;
	@FXML TableColumn<Book, String> genre;
	@FXML TableColumn<Book, String> category;
	@FXML TableColumn<Book, String> year;
	@FXML TableColumn<Book, String> bcnt;
	@FXML Button rental;


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
		cs.showWindow(s, "../program/userLogin.fxml", id5);
	}

	public void myPageProc() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/myPage.fxml", id5);
	}

	public void searchProc() {
		TextField tf = (TextField) root.lookup("#search");
		String search = tf.getText();
		
		List<Book> list = dao.searchBook(search);

		TableView<Book> bookList = (TableView<Book>) root.lookup("#searchBook");

		title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		bno.setCellValueFactory(new PropertyValueFactory<Book, String>("bno"));
		author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
		genre.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
		category.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
		year.setCellValueFactory(new PropertyValueFactory<Book, String>("year"));
		bcnt.setCellValueFactory(new PropertyValueFactory<Book, String>("bcnt"));

		bookList.getColumns().setAll(title, bno, author, publisher, genre, category, year, bcnt);

		ObservableList<Book> oList = FXCollections.observableArrayList(list);
		bookList.setItems(oList);
		
		rental.setOnAction(e -> {
			if (bookList.getSelectionModel().isEmpty()) {
				System.out.println("아무일도 일어나지 않음");
				return;
			} else {
				int selectedBno = bookList.getSelectionModel().getSelectedItem().getBno();
//				int selectedBcnt = bookList.getSelectionModel().getSelectedItem().getBcnt();
				
				
				if (dao.insertRental(selectedBno, id5)) {
					cs.errorBox("대여성공", "대출일자 : ", "반납일자 : ");
					
				} else {
					cs.errorBox("대여실패", "오류", "db에서 오류?");
				}
				
				
				
			}
		});
	}
	
	

}
