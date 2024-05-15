package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.controlsfx.control.Notifications;

public class MainController {

    @FXML
    private Button askBtn;

    @FXML
    private AnchorPane askUi;

    @FXML
    private AnchorPane dashboraUi;

    @FXML
    private Button dashboradBtn;

    @FXML
    private Button editBtn;

    @FXML
    private AnchorPane editUi;

    @FXML
    private Label labelUser;

    @FXML
    private Button notificationBtn;

    @FXML
    private AnchorPane notificationUi;

    @FXML
    private Button searchBtn;

    @FXML
    private AnchorPane searchUi;

    @FXML
    private Button timeBtn;

    @FXML
    private AnchorPane timeUi;
    
    
    @FXML
    private Label exit;
    
    @FXML
    private TableView<Trajet> tableMetros;
    
    @FXML
    private Button searchBtnS;
    
    
    @FXML
    private TextField departureNameS;
    
    
    
    
    @FXML
    private TextField ArrivalNameS;

    @FXML
    private Label timeLabel;
    @FXML
    private Button confirmBtn;
    @FXML
    private TextField departCityT;

    @FXML
    private TextField departTimeT;
    @FXML
    private TextField ArrivalCityT;
    
    
    
    private String nameUser;
	@FXML
	public void initialize() {
		initTableMetro();
		dashboradBtn.setOnAction(event -> {
			dashboraUi.setVisible(true);
	        notificationUi.setVisible(false);
	        searchUi.setVisible(false);
	        timeUi.setVisible(false);
	        askUi.setVisible(false);
	   
	        
	        // set the clicked button to white color
	        dashboradBtn.setStyle("-fx-background-color: white;  -fx-background-radius:20 0 0 20;-fx-text-fill: linear-gradient(to bottom right, #00caff, #7f00ff);");
	        
	        // set the other buttons to default color
	        notificationBtn.setStyle("-fx-background-color: transparent;");
	        searchBtn.setStyle("-fx-background-color: transparent;");
	        timeBtn.setStyle("-fx-background-color: transparent;");
	        askBtn.setStyle("-fx-background-color: transparent;");

	    });

	    notificationBtn.setOnAction(event -> {
	    	dashboraUi.setVisible(false);
	        notificationUi.setVisible(true);
	        searchUi.setVisible(false);
	        timeUi.setVisible(false);
	        askUi.setVisible(false);
	  
	        
	        // set the clicked button to white color
	        notificationBtn.setStyle("-fx-background-color: white; -fx-background-radius:20 0 0 20;-fx-text-fill: linear-gradient(to bottom right, #00caff, #7f00ff);");
	        
	        // set the other buttons to default color
	        dashboradBtn.setStyle("-fx-background-color: transparent;");
	        searchBtn.setStyle("-fx-background-color: transparent;");
	        timeBtn.setStyle("-fx-background-color: transparent;");
	        askBtn.setStyle("-fx-background-color: transparent;");
	
	        
	       
	        
	       TrajetDAO t=new TrajetDAO();
	       t.Notif();
	        
	    
	    });

	        searchBtn.setOnAction(event -> {
	    	dashboraUi.setVisible(false);
	        notificationUi.setVisible(false);
	        searchUi.setVisible(true);
	        timeUi.setVisible(false);
	        askUi.setVisible(false);
	   
	        
	        // set the clicked button to white color
	        searchBtn.setStyle("-fx-background-color: white; -fx-background-radius:20 0 0 20;-fx-text-fill: linear-gradient(to bottom right, #00caff, #7f00ff);");
	        
	        // set the other buttons to default color
	        dashboradBtn.setStyle("-fx-background-color: transparent;");
	        notificationBtn.setStyle("-fx-background-color: transparent;");
	        timeBtn.setStyle("-fx-background-color: transparent;");
	        askBtn.setStyle("-fx-background-color: transparent;");
	    
	      

	    });

	    
	    
	    timeBtn.setOnAction(event -> {
	    	dashboraUi.setVisible(false);
	        notificationUi.setVisible(false);
	        searchUi.setVisible(false);
	        timeUi.setVisible(true);
	        askUi.setVisible(false);
	       
	        
	        // set the clicked button to white color
	        timeBtn.setStyle("-fx-background-color: white; -fx-background-radius:20 0 0 20;-fx-text-fill: linear-gradient(to bottom right, #00caff, #7f00ff);");
	        
	        // set the other buttons to default color
	        dashboradBtn.setStyle("-fx-background-color: transparent;");
	        notificationBtn.setStyle("-fx-background-color: transparent;");
	        searchBtn.setStyle("-fx-background-color: transparent;");
	        askBtn.setStyle("-fx-background-color: transparent;");

	    });

	    
	    askBtn.setOnAction(event -> {
	    	dashboraUi.setVisible(false);
	        notificationUi.setVisible(false);
	        searchUi.setVisible(false);
	        timeUi.setVisible(false);
	        askUi.setVisible(true);
	      
	        
	        // set the clicked button to white color
	        askBtn.setStyle("-fx-background-color: white; -fx-background-radius:20 0 0 20;-fx-text-fill: linear-gradient(to bottom right, #00caff, #7f00ff);");
	        
	        // set the other buttons to default color
	        dashboradBtn.setStyle("-fx-background-color: transparent;");
	        notificationBtn.setStyle("-fx-background-color: transparent;");
	        searchBtn.setStyle("-fx-background-color: transparent;");
	        timeBtn.setStyle("-fx-background-color: transparent;");
	        
	  	  System.out.println("hello");
	      // Create a new instance of the chat room controller
	     
	       
	      // Load the chat room view in a new window
	      
	      
	    
	      try {
	    	  
		      FXMLLoader loader = new FXMLLoader(getClass().getResource("chatRoom.fxml"));
		        Parent root = loader.load();
		        ChatRoomController chatRoomController = loader.getController();
		        
		     
		        chatRoomController.init(nameUser);
	          Stage stage = new Stage();
	          stage.setScene(new Scene(root));
	          stage.show();
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	  
	     
	    });
	    
	    
	     // labelUser.setText(nameUser);
	    
	    // repeat the same for other buttons
	    // ...

	}
	

    @FXML
    void search(ActionEvent event) {
    
    	String departureCity = departureNameS.getText();
        String arrivalCity = ArrivalNameS.getText();
        
        TrajetDAO t=new TrajetDAO();
        t.searchMetro(departureCity,arrivalCity,tableMetros);


        
        }
  public void initTableMetro() {
	
    	
    	TrajetDAO t=new TrajetDAO();
       ResultSet rs= t.selection("select * from trajet");
       try {
    	 //initiliser les entetes
		ResultSetMetaData rsMd= rs.getMetaData();
		//class name on java
	int nbcl=rsMd.getColumnCount();
	for (int i=0;i<nbcl;i++)
	{
		TableColumn tc=new TableColumn(rsMd.getColumnName(i+1));
		tc.setCellValueFactory(new PropertyValueFactory<Trajet,Object>(rsMd.getColumnName(i+1)));
		tableMetros.getColumns().add(tc);
    	
	}

	
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 
}
  @FXML
  void confirmTime(ActionEvent event) {
	  
	   String departureTimec =departTimeT.getText();
	 
	  Time departureTime=Time.valueOf(departureTimec);
	      String departureCity = departCityT.getText();
	 
	      String arrivalCity = ArrivalCityT.getText();
	      TrajetDAO t=new TrajetDAO();
	      t.getTime(departureCity,arrivalCity,departureTime,timeLabel);

	 

  }
  @FXML
  void handleAskQuestionButton(ActionEvent event) {
  }


public void init(String nameUser) {
	// TODO Auto-generated method stub
	this.nameUser=nameUser;
}

@FXML
void Exit(MouseEvent event) {
Platform.exit();
}



}