package bookSelect;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class BookDB implements Initializable {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "system";
	String password = "oracle";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String sql = null;
	Alert alert;
	
	@FXML
	private ImageView bookImage, bookImage2;
	@FXML
	private TextField txtmsg;
	@FXML
	private TableView<BookSub> tableView;
	@FXML
	private TableColumn<BookSub, Integer> table_No;
	@FXML
	private TableColumn<BookSub, String> table_Title;
	@FXML
	private TableColumn<BookSub, String> table_Author;
	@FXML
	private TableColumn<BookSub, String> table_Publisher;
	@FXML
	private TableColumn<BookSub, String> table_Coment;
	@FXML
    private TextField bookAuthor;
    @FXML
    private TextField bookComent;
    @FXML
    private TextField bookNo;
    @FXML
    private TextField bookPublisher;
    @FXML
    private TextField bookTitle;
    @FXML
    private TextField bookState;
	
	//테이블 뷰를 이용하기위한 배열 생성
	ObservableList<BookSub> list = FXCollections.observableArrayList();
	 Image img = null;
	int cnt = 0;
	
	public void close() {
		System.exit(0);
	}
	//전체 검색 버튼 누를시 이벤트
	//db에 저장된 내용이 한번에 출력 버튼을 계속 누르면 기존에 있는 내용을 초기화 후 출력
	public void clickSelectAll(ActionEvent event) {
		Integer bno;
	    String bname;
	    String author;
	    String publisher;
	    String b_coment;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			sql = "select * from book2";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//테이블 열에 입력을 위한 코드
			table_No.setCellValueFactory(new PropertyValueFactory<BookSub, Integer>("bno"));
			table_Title.setCellValueFactory(new PropertyValueFactory<BookSub, String>("bname"));
			table_Author.setCellValueFactory(new PropertyValueFactory<BookSub, String>("author"));
			table_Publisher.setCellValueFactory(new PropertyValueFactory<BookSub, String>("publisher"));
			table_Coment.setCellValueFactory(new PropertyValueFactory<BookSub, String>("b_coment"));
			
			if (cnt == 0) {
				while (rs.next()) {
					bno = rs.getInt("bno");
					bname = rs.getString("bname");
					author = rs.getString("author");
					publisher = rs.getString("publisher");
					b_coment = rs.getString("b_coment");

					list.add(new BookSub(bno,bname,author,publisher,b_coment));
				}

				tableView.setItems(list);
				cnt++;
				//cnt를 통해 다시 클릭시 기존에 있는 내용을 지우고 내용을 다시 받고 출력
			} else {
				tableView.getItems().clear();

				while (rs.next()) {
					bno = rs.getInt("bno");
					bname = rs.getString("bname");
					author = rs.getString("author");
					publisher = rs.getString("publisher");
					b_coment = rs.getString("b_coment");

					list.add(new BookSub(bno,bname,author,publisher,b_coment));
				}
				tableView.setItems(list);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//기본검색기능
	public void clickSelect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			sql = "select * from book2 where bname = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, txtmsg.getText());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bookNo.setText(rs.getString("BNO"));
				bookTitle.setText(rs.getString("BNAME"));
				bookAuthor.setText(rs.getString("AUTHOR"));
				bookPublisher.setText(rs.getString("PUBLISHER"));
				bookState.setText(rs.getString("STATE"));
				img = new Image(getClass().getResourceAsStream(rs.getString("IMG")));
				bookImage.setImage(img);
				//db에 img2를추가 하여 사진을 겹침
				img = new Image(getClass().getResourceAsStream(rs.getString("IMG2")));
				bookImage2.setImage(img);
				txtmsg.setText("");
			} else {
				// Alert : 버튼 클릭시 새로운 메시지 창이 나온다.
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("도서 정보");
				alert.setContentText("도서 정보가 없습니다.");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void clickrent(ActionEvent event) {
		Parent root;
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("bookrent.fxml"));
		try {
			root = loader.load();
			stage.setScene(new Scene(root));
			stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
