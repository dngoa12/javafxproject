package bookmanagement;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.DAO;
import dao.DAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.AdminService;
import service.AdminServiceImpl;
import service.CommonService;
import service.CommonServiceImpl;

public class AdminController extends InitController implements Initializable{

	private Parent root;
	private CommonService cs;
	private AdminService as;
	private DAO dao;

	@FXML TableColumn<Book, String> booktitle;
	@FXML TableColumn<Book, Integer> booknum;
	@FXML TableColumn<Book, String> bookauthor;
	@FXML TableColumn<Book, String> bookpublisher;
	@FXML TableColumn<Book, String> bookgenre;
	@FXML TableColumn<Book, String> bookcategory;
	@FXML TableColumn<Book, Integer> bookyear;
	@FXML TableColumn<Book, Integer> bookcount;
	@FXML TableColumn<User, String> userName;
	@FXML TableColumn<User, String> userId;
	@FXML TableColumn<User, String> userPw;




	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		as = new AdminServiceImpl();
		dao = new DAOImpl();
	}

	@Override
	public void setRoot(Parent root) {
		this.root = root;

	}

	public void logoutProc(ActionEvent event) {
		// 어드민1-5창에서 로그아웃 하는 메서드
		cs.logout(event);
	}

	
	public void cancelProc(ActionEvent event) {
		// 
		cs.windowClose(event);
	}

	public void admin2() {
		// admin2 -> 도서등록
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../bookmanagement/admin2.fxml");
	}

	public void admin() {
		// admin -> 도서목록
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../bookmanagement/admin.fxml");
	}

	public void admin3() {
		// admin3 -> 회원목록
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../bookmanagement/admin3.fxml");
	}

	public void admin4() {
		// admin4 -> 연체관리
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../bookmanagement/admin4.fxml");
	}

	public void admin5() {
		// admin5 -> 공지사항관리
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../bookmanagement/admin5.fxml");
	}

	public void addBookProc() {
		// admin2에서 도서를 등록하는 메서드
		Book b = new Book();
		TextField tfTitle = (TextField) root.lookup("#title");
		TextField tfBook_num = (TextField) root.lookup("#book_num");
		TextField tfAuthor = (TextField) root.lookup("#author");
		TextField tfPublisher = (TextField) root.lookup("#publisher");
		TextField tfGenre = (TextField) root.lookup("#genre");
		TextField tfCategory = (TextField) root.lookup("#category");
		TextField tfYear = (TextField) root.lookup("#year");
		TextField tfBook_count = (TextField) root.lookup("#book_count");

		String[] txtEmpty = {tfTitle.getText(), tfBook_num.getText(), tfAuthor.getText(),
				tfPublisher.getText(), tfGenre.getText(), tfCategory.getText(), tfYear.getText(), tfBook_count.getText()};

		String[] txtEmptyName = {"제목", "도서번호", "작가", "출판사", "분야", "카테고리", "출판년도", "재고"};

		for (int i=0;i<txtEmpty.length;i++) {
			if (cs.isEmpty(txtEmpty[i])) {
				cs.errorBox("오류", "오류발견", txtEmptyName[i]+"창이 비었음");
				return;
			}
		}

		b.setTltle(tfTitle.getText());
		b.setBook_num(Integer.parseInt(tfBook_num.getText()));
		b.setAuthor(tfAuthor.getText());
		b.setPublisher(tfPublisher.getText());
		b.setGenre(tfGenre.getText());
		b.setCategory(tfCategory.getText());
		b.setYear(Integer.parseInt(tfYear.getText()));
		b.setBook_count(Integer.parseInt(tfBook_count.getText()));

		if (dao.insertBook(b)) {
			cs.errorBox("등록", "성공", "도서등록이 정상적으로 이루어짐");

		} else {
			cs.errorBox("등록", "실패", "db입력중 문제 발생");
			return;
		}


	}

	public void deleteBookProc() {
		// admin2에서 도서를 삭제하는 메서드
		Book b = new Book();
		TextField tfBook_num = (TextField) root.lookup("#book_num");

		if (cs.isEmpty(tfBook_num.getText())) {
			cs.errorBox("오류", "오류발견", tfBook_num.getText()+"창이 비었음");
			return;
		}

		b.setBook_num(Integer.parseInt(tfBook_num.getText()));

		if (dao.deleteBook(b)) {
			cs.errorBox("삭제", "성공", "도서삭제가 정상적으로 이루어짐");
		} else {
			cs.errorBox("삭제", "실패", "db삭제에서 문제 발생");
			return;
		}

	}

	public void showBookColumn() {
		// admin에 있는 테이블뷰에 목록보기 버튼을 클릭하면 입력된 도서들을 띄우는 메서드
		List<Book> list = dao.allBook();
		TableView<Book> bookList = (TableView<Book>) root.lookup("#booklist");

		booktitle.setCellValueFactory(new PropertyValueFactory<Book, String>("tltle"));
		booknum.setCellValueFactory(new PropertyValueFactory<Book, Integer>("book_num"));
		bookauthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		bookpublisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
		bookgenre.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
		bookcategory.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
		bookyear.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
		bookcount.setCellValueFactory(new PropertyValueFactory<Book, Integer>("book_count"));

		bookList.getColumns().setAll(booktitle, booknum, bookauthor, bookpublisher, bookgenre, bookcategory, bookyear, bookcount);

		ObservableList<Book> memberList = FXCollections.observableArrayList(list);
		bookList.setItems(memberList);

		bookList.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1) {
				int num = bookList.getSelectionModel().getSelectedItem().getBook_num();
				
				
				Stage s = new Stage();
				root = cs.showWindow(s, "../bookmanagement/eachBook.fxml");
				
				eachBookState(num, root);
			}
		});


	}

	public void showUserColumn() {
		// admin3에 있는 테이블뷰에 목록보기 버튼을 클릭하면 입력된 회원들을 띄우는 메서드
		List<User> list = dao.allUser();
		TableView<User> userList = (TableView<User>) root.lookup("#userList");

		userName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		userId.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		userPw.setCellValueFactory(new PropertyValueFactory<User, String>("pw"));

		userList.getColumns().setAll(userName, userId, userPw);

		ObservableList<User> oList = FXCollections.observableArrayList(list);
		userList.setItems(oList);


	}


	public void eachBookState(int num, Parent root) {
		// admin의 테이블뷰에 있는 도서를 클릭하면 해당하는 도서의 상태를 띄우는 메서드
		Book b = dao.eachBook(num);
		
		Label l1 = (Label) root.lookup("#eachtitle");
		Label l2 = (Label) root.lookup("#eachnum");
		Label l3 = (Label) root.lookup("#eachauthor");
		Label l4 = (Label) root.lookup("#eachpublisher");
		Label l5 = (Label) root.lookup("#eachgenre");
		Label l6 = (Label) root.lookup("#eachcategory");
		Label l7 = (Label) root.lookup("#eachyear");
		Label l8 = (Label) root.lookup("#eachcount");
		
		l1.setText(b.getTltle());
		l2.setText(String.valueOf(b.getBook_num()));
		l3.setText(b.getAuthor());
		l4.setText(b.getPublisher());
		l5.setText(b.getGenre());
		l6.setText(b.getCategory());
		l7.setText(String.valueOf(b.getYear()));
		l8.setText(String.valueOf(b.getBook_count()));
		
		ImageView iv = (ImageView) root.lookup("#eachImage");
		
		if (l2.getText().equals("1")) {
			iv.setImage(new Image("/image/oracle.jpg"));
		} else if (l2.getText().equals("2")) {
			iv.setImage(new Image("/image/java.jpg"));
		} else if (l2.getText().equals("3")) {
			iv.setImage(new Image("/image/sapiens.jpg"));
		} else if (l2.getText().equals("4")) {
			iv.setImage(new Image("/image/faust1.jpg"));
		}
	}


}
