package bookmanagement;

import javafx.application.Application;
import javafx.stage.Stage;
import service.CommonService;
import service.CommonServiceImpl;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// 공통서비스 쇼윈도우로 기본창 실행
		CommonService cs = new CommonServiceImpl();
		cs.showWindow(primaryStage, "../bookmanagement/login.fxml");
		
	}
	
	
	public static void main(String[] args) {
		launch();
	}
}
