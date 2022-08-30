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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service3.CommonService;
import service3.CommonServiceImpl;

public class AdminController extends InitController implements Initializable{
	
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
	@FXML TableColumn<Book, String> bcnt;
	private EachBookController ebc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
		ebc = new EachBookController();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void logoutProc() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/adminLogin.fxml", id5);
	}
	
	public void admin2() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/admin2.fxml", id5);
	}
	
	public void admin3() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/admin3.fxml", id5);
	}
	
	public void admin4() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/admin4.fxml", id5);
	}
	
	public void admin5() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/admin5.fxml", id5);
	}
	
	public void showBookColumn() {
		List<Book> l = dao.allBook();
		TableView<Book> bookList = (TableView<Book>) root.lookup("#bookList");
		
		title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		bno.setCellValueFactory(new PropertyValueFactory<Book, String>("bno"));
		author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
		bcnt.setCellValueFactory(new PropertyValueFactory<Book, String>("bcnt"));
		
		bookList.getColumns().setAll(title, bno, author, publisher, bcnt);
		
		ObservableList<Book> oList = FXCollections.observableArrayList(l);
		bookList.setItems(oList);
		
		bookList.setOnMouseClicked(e -> {
			if (e.getClickCount()>1) {
				int num = bookList.getSelectionModel().getSelectedItem().getBno();
				
				Stage s = new Stage();
				root = cs.showWindow(s, "../login/eachBook.fxml", id5);
				
				eachBookState(num, root);
				
			}
		});
		
	}
	
	public void eachBookState(int num, Parent root) {
		Book b = dao.eachBook(num);
		
		Label l1 = (Label) root.lookup("#eachTitle");
		Label l2 = (Label) root.lookup("#eachBno");
		Label l3 = (Label) root.lookup("#eachAuthor");
		Label l4 = (Label) root.lookup("#eachPublisher");
		
		Label l8 = (Label) root.lookup("#eachBcnt");
		
		l1.setText(b.getTitle());
		l2.setText(String.valueOf(b.getBno()));
		l3.setText(b.getAuthor());
		l4.setText(b.getPublisher());
		l8.setText(String.valueOf(b.getBcnt()));
		
		ImageView iv = (ImageView) root.lookup("#eachImage");
		
		if (l1.getText().equals("금영석교수님의 코딩비법")) {
			iv.setImage(new Image("/img/javabook.png"));
		} else if (l1.getText().equals("마님은 왜 돌쇠에게만 흰 쌀밥을 주었을까?")) {
			iv.setImage(new Image("/img/rice.png"));
		} else if (l1.getText().equals("이순신 위인전")) {
			iv.setImage(new Image("/img/who_leesunsin.jpg"));
		} else if (l1.getText().equals("사춘기와 성")) {
			iv.setImage(new Image("/img/who_sachungi.jpg"));
		} else if (l1.getText().equals("그리스 로마 신화")) {
			iv.setImage(new Image("/img/greece.jpg"));
		} else if (l1.getText().equals("마법천자문")) {
			iv.setImage(new Image("/img/mabubchunjamun.jpg"));
		} else if (l1.getText().equals("신")) {
			iv.setImage(new Image("/img/sin.jpg"));
		} else if (l1.getText().equals("제3 인류")) {
			iv.setImage(new Image("/img/3rd_people.jpg"));
		} else if (l1.getText().equals("무인도에서 살아남기")) {
			iv.setImage(new Image("/img/mooindo.jpg"));
		}
	}

}
