package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class dashboradController {

    @FXML
    private VBox SignUpForm;

    @FXML
    private TextField confirmPwd;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private HBox formSignUP;

    @FXML
    private TextField lastName;

    @FXML
    private TextField pwd;

    @FXML
    private Button signUPbtn;

    @FXML
    private TextField userName;
    
    private userDAO userDao; // instance variable
    @FXML
    private ImageView previous;

    public dashboradController() {
		super();
		this.userDao = new userDAO();
	}


	@FXML
    void SignUP(ActionEvent event) {
    	
     int a=this.userDao.insert(firstName.getText(), lastName.getText(), userName.getText(), pwd.getText(), email.getText(), "User");

    }
    @FXML

    void handleClick(MouseEvent event) {
     	 Stage currentStage = (Stage) previous.getScene().getWindow();
     	 currentStage.close();
    }
}