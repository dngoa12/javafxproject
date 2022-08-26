package program;

import javafx.application.Application;
import javafx.stage.Stage;
import service2.CommonService;
import service2.CommonServiceImpl;


public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		CommonService cs = new CommonServiceImpl();
		cs.showWindow(primaryStage, "../program/userLogin.fxml", "");
		
	}
	
	
	public static void main(String[] args) {
		launch();
	}
}
