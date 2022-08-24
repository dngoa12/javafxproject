package bookmanagement;

import java.net.URL;
import java.util.ResourceBundle;

import dao.DAO;
import dao.DAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import service.CommonService;
import service.CommonServiceImpl;

public class EachController extends InitController implements Initializable{

	private Parent root;
	private CommonService cs;
	private DAO dao;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void cancelProc(ActionEvent event) {
		// 이치북창을 꺼주는 메서드
		cs.windowClose(event);
	}
	
	
	
}
