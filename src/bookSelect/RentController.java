package bookSelect;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class RentController implements Initializable {

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
	private AnchorPane pane;
	@FXML
	private TextField rent_Title;
	@FXML
	private TextField rent_No;
	@FXML
	private TextField rent_date;
	@FXML
	private TextField return_date;
	@FXML
	private Button rent_btn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 3);
		String time = sdf1.format(cal.getTime());
		return_date.setText(time);
		String time2 = sdf1.format(date);
		rent_date.setText(time2);

	}

	public void rentbtn(ActionEvent event) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			sql = "update book2 set state = '대여불가능' where bno = ? and bname = ? and state = '대여가능'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rent_No.getText());
			pstmt.setString(2, rent_Title.getText());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("도서 정보");
				alert.setContentText("대여완료");
				alert.show();
			} else if (rent_No.getText().equals("") || rent_Title.getText().equals("")) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("도서 정보");
				alert.setContentText("책의 정보를 입력하세요");
				alert.show();
			} else {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("도서 정보");
				alert.setContentText("이미 대여된 도서");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
