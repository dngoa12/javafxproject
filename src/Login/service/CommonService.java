package Login.service;

import Login.User;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface CommonService {
	public void WindowClose(ActionEvent event);
	public Parent showWindow(Stage s, String formPath);
	public void errorBox(String title, String header, String content);
	public void errorBox(String header, String content);
	public void errorBox(String content);
}