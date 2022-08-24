package service;

import bookmanagement.InitController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{

	@Override
	public void windowClose(ActionEvent event) {
		Parent root = (Parent) event.getSource();
		Stage s = (Stage) root.getScene().getWindow();
		s.close();
		
	}

	@Override
	public Parent showWindow(Stage s, String fxml) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		Parent root = null;
		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		InitController ic = loader.getController();
		ic.setRoot(root);
		
		s.show();
		
		return root;
	}

	@Override
	public void errorBox(String tltle, String header, String content) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle(tltle);
		a.setHeaderText(header);
		a.setContentText(content);
		a.showAndWait();
		
	}

	@Override
	public void logout(ActionEvent event) {
		errorBox("로그아웃", "로그아웃성공", "로그아웃이 정상적으로 이루어짐");
		Parent root = (Parent) event.getSource();
		Stage s = (Stage) root.getScene().getWindow();
		showWindow(s, "../bookmanagement/login.fxml");
		
	}

	@Override
	public boolean isEmpty(String str) {
		if (str.isEmpty()) {
			return true;
		}
		return false;
	}

}
