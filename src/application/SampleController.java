package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SampleController {
    @FXML
    private VBox SignUpForm;

    @FXML
    private Button btnLogin1;

    @FXML
    private RadioButton btn_admin;

    @FXML
    private RadioButton btn_user;

   
    @FXML
    private Label exit;
    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPwd;

    @FXML
    private VBox signInForm;

    @FXML
    private Button signUPbtn;

    @FXML
    private Label sign_up;

    @FXML
    private ToggleGroup user;
    
    
    private userDAO userDao; // instance variable
  
    
    public SampleController() {
        userDao = new userDAO(); // create an instance of userDAO in the constructor
       
    }
    
    
	@FXML
	public void initialize() {
		
	
	     

		
	}
    
    
    @FXML
    
    void signIn(ActionEvent event) {
    	
    	
        String name = inputName.getText();
        String password = inputPwd.getText();
        Stage currentStage = (Stage) btnLogin1.getScene().getWindow();

      
    
        
        RadioButton selectedRadioButton = (RadioButton) user.getSelectedToggle();
        String selectedUserType = selectedRadioButton.getText();
        
        if (selectedUserType.equals("User")) {
            // open user window
        	   userDao.verifyUser(name, password,"User" ,currentStage);
        } else if (selectedUserType.equals("Admin")) {
        	  userDao.verifyUser(name, password,"Admin" ,currentStage);
        }
    

      
    }
    @FXML
    public void newSignUP() {
        // Add a click event listener to the "signup" label
    	sign_up.setOnMouseClicked(event -> {
            try {
            	
            	 Stage currentStage = (Stage) sign_up.getScene().getWindow();
             
                // Load the FXML file for the dashboard
            	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Dashborad.fxml"));
                


    	        Rectangle rectangle = new Rectangle(885.0, 555);
    	        rectangle.setArcWidth(30);
    	        rectangle.setArcHeight(30);
    	        
    			root.setClip(rectangle);
    			Scene scene = new Scene(root,885.0,555.0,Color.TRANSPARENT);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			Stage primaryStage=new Stage();
				primaryStage.initStyle(StageStyle.TRANSPARENT);
    			primaryStage.setScene(scene);
    			primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });}
    
    public void createAccount()
    {
    	try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Dashborad"));

	        Rectangle rectangle = new Rectangle(885.0, 555);
	        rectangle.setArcWidth(30);
	        rectangle.setArcHeight(30);
	        
			root.setClip(rectangle);
			Scene scene = new Scene(root,885.0,555.0,Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 Stage primaryStage = new Stage();
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }  
    @FXML
    void Exit(MouseEvent event) {
   Platform.exit();
    }
 
}


