package application;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AdminController {

    @FXML
    private TextField ArrivalNameS;

    @FXML
    private TextField ArrivalNameS1;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Button MUserBtn;

    @FXML
    private AnchorPane ManagementMetro;

    @FXML
    private AnchorPane ManagementUser;

    @FXML
    private Button MmetroBtn;

    @FXML
    private TextField departureNameS;

    @FXML
    private TextField departureNameS1;

    @FXML
    private TextField departureNameS2;

    @FXML
    private TextField tmeDepart;

    @FXML
    private TextField IdMetro;

    @FXML
    private TextField idStationDepart;

    @FXML
    private TextField idStationarrivee;

    @FXML
    private TextField ArrivalTime;
    

    @FXML
    private TableView<User> tabelUsers;

    @FXML
    private TableView<Trajet> tableMetros;

    
    @FXML
    private Button deleteBtnm;
    
    
    @FXML
    private Label exit;
  

    @FXML
    private Button InsertBtn;
    @FXML
    private Button updateBtn;
    
    ObservableList<Trajet> data1;
    ObservableList<User> data2;
    User selectedItem;
    @FXML
	public void initialize() {
    	initTableUser();
		initTableMetro();
    	MmetroBtn.setOnAction(event -> {
    		ManagementMetro.setVisible(true);
    		ManagementUser.setVisible(false);
	      
	        
	        // set the clicked button to white color
    		MmetroBtn.setStyle("-fx-background-color: white;  -fx-background-radius:20 0 0 20;-fx-text-fill: linear-gradient(to bottom right, #00caff, #7f00ff);");
	        
	        // set the other buttons to default color
    		MUserBtn.setStyle("-fx-background-color: transparent;");
	   
	    });
    	
    	

    	MUserBtn.setOnAction(event -> {
    		ManagementMetro.setVisible(false);
    		ManagementUser.setVisible(true);
	      
	        
	        // set the clicked button to white color
    		MUserBtn.setStyle("-fx-background-color: white;  -fx-background-radius:20 0 0 20;-fx-text-fill: linear-gradient(to bottom right, #00caff, #7f00ff);");
	        
	        // set the other buttons to default color
    		MmetroBtn.setStyle("-fx-background-color: transparent;");
	   
	    });
    	
    }
    
    
    @FXML
    void deleteM(ActionEvent event) {

        // Get the selected item
        Trajet selectedItem = tableMetros.getSelectionModel().getSelectedItem();

        // Delete the selected item from the TableView
     

    
  userDAO u=new userDAO();
  tableMetros.getItems().remove(selectedItem);
   u.delete(selectedItem.getId());
   tableMetros.refresh();
    }

    @FXML
    void insert(ActionEvent event) {
             TrajetDAO t=new TrajetDAO();
             t.insert(Integer.parseInt(IdMetro.getText()),Integer.parseInt(idStationDepart.getText()) ,Integer.parseInt(idStationarrivee.getText()) ,Time.valueOf(tmeDepart.getText()),Time.valueOf(ArrivalTime.getText())  ); 
             
          // Create a new object of the data model class for the TableView
             Trajet tr= new Trajet (Integer.parseInt(IdMetro.getText()),Integer.parseInt(idStationDepart.getText()) ,Integer.parseInt(idStationarrivee.getText()) ,Time.valueOf(tmeDepart.getText()),Time.valueOf(ArrivalTime.getText())  );

             // Add the new object to the ObservableList that is used as the data source for the TableView
             tableMetros.getItems().add(tr);
             tableMetros.refresh();
    }

    @FXML
    void upDate(ActionEvent event) {

    }
    @FXML
    void DeleteBtn(ActionEvent event) {
   
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

    data1=FXCollections.observableArrayList();

    
    while (rs.next())
    {
    	int id=rs.getInt(rsMd.getColumnName(1));
    	int id_station_arrivee=rs.getInt(rsMd.getColumnName(2));
    	int id_station_depart=rs.getInt(rsMd.getColumnName(3));
    	Time heure_depart=rs.getTime(rsMd.getColumnName(4));
    	Time heure_arrivee=rs.getTime(rsMd.getColumnName(5));
 	   
 	   
 	   data1.add(new Trajet(id, id_station_arrivee, id_station_depart,  heure_depart, heure_arrivee));
    }
      tableMetros.setItems(data1);
	
	
	
	
	
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 
}
  public void initTableUser() {
		
  	
  	userDAO e=new userDAO();
     ResultSet rs= e.selection("select * from users");
     try {
  	 //initiliser les entetes
		ResultSetMetaData rsMd= rs.getMetaData();
		//class name on java
	int nbcl=rsMd.getColumnCount();
	for (int i = 0; i < nbcl; i++) {
	    TableColumn tc = new TableColumn(rsMd.getColumnName(i + 1));
	    if (rsMd.getColumnName(i + 1).equals("id")) {
	        tc.setCellValueFactory(new PropertyValueFactory<User, Object>("id"));
	    } else if (rsMd.getColumnName(i + 1).equals("firstName")) {
	        tc.setCellValueFactory(new PropertyValueFactory<User, Object>("firstName"));
	    } else if (rsMd.getColumnName(i + 1).equals("lastName")) {
	        tc.setCellValueFactory(new PropertyValueFactory<User, Object>("lastName"));
	    } else if (rsMd.getColumnName(i + 1).equals("userName")) {
	        tc.setCellValueFactory(new PropertyValueFactory<User, Object>("username"));
	    } else if (rsMd.getColumnName(i + 1).equals("password")) {
	        tc.setCellValueFactory(new PropertyValueFactory<User, Object>("password"));
	    } else if (rsMd.getColumnName(i + 1).equals("email")) {
	        tc.setCellValueFactory(new PropertyValueFactory<User, Object>("email"));
	    } else if (rsMd.getColumnName(i + 1).equals("type")) {
	        tc.setCellValueFactory(new PropertyValueFactory<User, Object>("type"));
	    }
	
		tabelUsers.getColumns().add(tc);
	}
	//initiliser données
    data2=FXCollections.observableArrayList();

  
    while (rs.next())
    {
    	int id=rs.getInt(rsMd.getColumnName(1));
    	 String firstName=rs.getString(rsMd.getColumnName(2));
    	 String lastName=rs.getString(rsMd.getColumnName(3));
    	 String userName=rs.getString(rsMd.getColumnName(4));
    	 String password=rs.getString(rsMd.getColumnName(5));
    	 String email=rs.getString(rsMd.getColumnName(6));
    	 String type=rs.getString(rsMd.getColumnName(7));
    	 
 	  data2.add(new User(id,  firstName,  lastName,  userName,  password, email, type));
    }
  tabelUsers.setItems(data2);
	
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
tabelUsers.refresh();
}

  @FXML
  void deleteUser(ActionEvent event) {
	 
	        // Get the selected item
	        User selectedItem = tabelUsers.getSelectionModel().getSelectedItem();

	        // Delete the selected item from the TableView
	     

	    
	  userDAO u=new userDAO();
	   tabelUsers.getItems().remove(selectedItem);
       u.delete(selectedItem.getId());
       tabelUsers.refresh();
       
  }
  @FXML
  void Exit(MouseEvent event) {
   Platform.exit();
  }

}