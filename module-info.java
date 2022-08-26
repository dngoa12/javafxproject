module Javafx_ex01 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	
	
	opens bookSelect to javafx.graphics, javafx.fxml,javafx.base;
	
}
