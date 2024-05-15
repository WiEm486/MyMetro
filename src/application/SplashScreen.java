package application;








import java.io.IOException;
import java.net.Socket;
import java.text.NumberFormat.Style;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;






public class SplashScreen extends Application {

    private static final int SPLASH_SCREEN_TIME = 5000; // durée d'affichage de l'écran de chargement en millisecondes
    
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    private Stage primaryStage;
    private Socket clientSocket;
    
    @Override
    public void start(Stage splashStage) throws Exception {
        // création de l'interface de chargement
        ImageView imageView = new ImageView(new Image("loading.gif")); // chargement d'une image gif de chargement
        StackPane rot = new StackPane(imageView);
        Scene scenee = new Scene(rot);
        splashStage.setScene(scenee);
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.show();
        
        // utilisation d'un Thread pour attendre le temps nécessaire avant d'afficher la fenêtre principale
        new Thread(() -> {
            try {
                Thread.sleep(SPLASH_SCREEN_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> { // affichage de la fenêtre principale dans l'EDT de JavaFX
                Stage primaryStage = new Stage();
                BorderPane root;
				try {
					root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
					 Rectangle rectangle = new Rectangle(885.0, 555);
		    	        rectangle.setArcWidth(30);
		    	        rectangle.setArcHeight(30);
		    	        
		    			root.setClip(rectangle);

		    			Scene scene = new Scene(root,885.0,555.0,Color.TRANSPARENT);
		    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    			primaryStage.initStyle(StageStyle.TRANSPARENT);
		    			primaryStage.setScene(scene);
		                primaryStage.show();
		                splashStage.hide(); // fermeture de l'écran de chargement
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
            });
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}









