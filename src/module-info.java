module MyProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;

	requires org.controlsfx.controls;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
