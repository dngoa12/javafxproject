package Login;

import Login.service.CommonService;

import Login.service.CommonServiceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class LoginMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		CommonService comServ = new CommonServiceImpl();
		comServ.showWindow(primaryStage, "../LoginForm.fxml");
	}

	public static void main(String[] args) {
		launch(args);
	}
}


