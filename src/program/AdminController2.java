package program;

import java.net.URL;
import java.util.ResourceBundle;

import dao2.DAO;
import dao2.DAOImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service2.CommonService;
import service2.CommonServiceImpl;

public class AdminController2 extends InitController implements Initializable{

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

	public void admin1() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin1.fxml", id5);
	}

	public void admin3() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin3.fxml", id5);
	}

	public void admin4() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin4.fxml", id5);
	}

	public void admin5() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../program/admin5.fxml", id5);
	}

	public void addBookProc() {
		Book b = new Book();
		TextField tfTitle = (TextField) root.lookup("#title");
		TextField tfBook_num = (TextField) root.lookup("#bno");
		TextField tfAuthor = (TextField) root.lookup("#author");
		TextField tfPublisher = (TextField) root.lookup("#publisher");
		TextField tfGenre = (TextField) root.lookup("#genre");
		TextField tfCategory = (TextField) root.lookup("#category");
		TextField tfYear = (TextField) root.lookup("#year");
		TextField tfBook_count = (TextField) root.lookup("#bcnt");

		String[] txtEmpty = {tfTitle.getText(), tfBook_num.getText(), tfAuthor.getText(),
				tfPublisher.getText(), tfGenre.getText(), tfCategory.getText(), tfYear.getText(), tfBook_count.getText()};

		String[] txtEmptyName = {"제목", "도서번호", "작가", "출판사", "분야", "카테고리", "출판년도", "재고"};

		for (int i=0;i<txtEmpty.length;i++) {
			if (cs.isEmpty(txtEmpty[i])) {
				cs.errorBox("오류", "오류발견", txtEmptyName[i]+"창이 비었음");
				return;
			}
		}

		b.setTitle(tfTitle.getText());
		b.setBno(Integer.parseInt(tfBook_num.getText()));
		b.setAuthor(tfAuthor.getText());
		b.setPublisher(tfPublisher.getText());
		b.setGenre(tfGenre.getText());
		b.setCategory(tfCategory.getText());
		b.setYear(Integer.parseInt(tfYear.getText()));
		b.setBcnt(Integer.parseInt(tfBook_count.getText()));

		if (dao.insertBook(b)) {
			cs.errorBox("등록", "성공", "도서등록이 정상적으로 이루어짐");
			tfTitle.clear();
			tfBook_num.clear();
			tfAuthor.clear();
			tfPublisher.clear();
			tfGenre.clear();
			tfCategory.clear();
			tfYear.clear();
			tfBook_count.clear();
			tfTitle.requestFocus();
		} else {
			cs.errorBox("등록", "실패", "db입력중 문제 발생");
			return;
		}
	}

	public void modifyBookProc() {
		Book b = new Book();
		TextField tfBook_num = (TextField) root.lookup("#bno");
		TextField tfYear = (TextField) root.lookup("#year");
		TextField tfBook_count = (TextField) root.lookup("#bcnt");

		String[] empty = {tfBook_num.getText(), tfYear.getText(), tfBook_count.getText()};
		String[] emptyName = {"도서번호", "수정할 출판년도", "수정할 재고"};

		for (int i=0;i<empty.length;i++) {
			if (cs.isEmpty(empty[i])) {
				cs.errorBox("오류", "수정오류", emptyName[i]+" 입력창이 비었음");
				return;
			}
		}

		b.setBno(Integer.parseInt(tfBook_num.getText()));
		b.setYear(Integer.parseInt(tfYear.getText()));
		b.setBcnt(Integer.parseInt(tfBook_count.getText()));

		if (dao.modifyBook(b)) {
			cs.errorBox("수정", "성공", "도서정보수정이 정상적으로 이루어짐");
			tfBook_num.clear();
			tfYear.clear();
			tfBook_count.clear();
		} else {
			cs.errorBox("수정", "실패", "db수정에서 문제 발생");
			return;
		} 


	}
	
	public void deleteBookProc() {
		Book b = new Book();
		TextField tfBook_num = (TextField) root.lookup("#bno");

		if (cs.isEmpty(tfBook_num.getText())) {
			cs.errorBox("오류", "오류발견", "도서번호 창이 비었음");
			return;
		}

		b.setBno(Integer.parseInt(tfBook_num.getText()));

		if (dao.deleteBook(b)) {
			cs.errorBox("삭제", "성공", "도서삭제가 정상적으로 이루어짐");
			tfBook_num.clear();
		} else {
			cs.errorBox("삭제", "실패", "db삭제에서 문제 발생");
			return;
		}
	}

}
