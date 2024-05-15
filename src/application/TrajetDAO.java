
package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.controlsfx.control.Notifications;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TrajetDAO {
	Connection con= null;
	Statement st=null;
	private Alert alert1;
	
public TrajetDAO() {
	con=MaConnexion2.connecter();
	if(con!=null)
	{
		try {
			st=con.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
public int insert(int id,int  idStationDepart,int idStationArrive,Time heureDepart,Time heureArrive)
{
	if(con!=null) {
		String req="insert into trajet (id,id_station_depart,id_station_arrivee,heure_depart,heure_arrivee)values(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(req);
			ps.setInt(1, id);
			ps.setInt(2,idStationDepart);
			ps.setInt(3,idStationArrive);
			ps.setTime(4, heureDepart);
			ps.setTime(5,heureArrive);
			
			System.out.print("valid ");
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	
	}
	return 0;
}






public void Notif()
{
	 // Get the current time in the format used in the database (HH:mm:ss)
    LocalTime currentTime = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String formattedTime = currentTime.format(formatter);

    // Execute a query to find any trips departing at the current time
    String departure = "";
    String destination = "";
 
         Statement stmt;
		try {
			stmt = con.createStatement();
			  ResultSet rs;
			  String sql = "     SELECT t.heure_depart,  s1.nom as ville_depart ,  s2.nom as ville_arrivee\r\n"
		        		+ "                FROM trajet t, station s1, station s2\r\n"
		        		+ "                WHERE t.id_station_depart = s1.id\r\n"
		        		+ "                AND  t.id_station_arrivee = s2.id\r\n"
		        		+ "                AND t.heure_depart  ='" + formattedTime + "'";
		        
			rs = stmt.executeQuery(sql);
			   if (rs.next()) {
		            departure = rs.getString(2);
		            destination = rs.getString(3);
		        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        
 

    // Show a notification with the same information
    if (!departure.isEmpty() && !destination.isEmpty()) {
        Notifications.create()
                .title("Départ imminent !")
                .text("Le métro part maintenant de " + departure + " vers " + destination)
                .showInformation();
    }
    else {
    	 Notifications.create()
         .title("News")
         .text("No Metro will go at this Moment")
         .showInformation();
    }
    
}
public void searchMetro(String departureCity, String arrivalCity, TableView<Trajet> tableMetros)
{
	 PreparedStatement stmt = null;
     ResultSet rs = null;
     String query = "SELECT * FROM trajet \r\n"
     		+ "WHERE id_station_depart IN (SELECT id FROM station WHERE nom = ?)\r\n"
     		+ "AND id_station_arrivee IN (SELECT id FROM station WHERE nom = ?)\r\n"
     		+ "AND heure_depart >= CURTIME()\r\n"
     		+ "ORDER BY heure_depart ASC\r\n"
     		+ "LIMIT 10;";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, departureCity);
	         stmt.setString(2, arrivalCity);
	         rs = stmt.executeQuery();
	         ObservableList<Trajet> trajets = FXCollections.observableArrayList();
	         while (rs.next()) {
	             int id = rs.getInt("id");
	             Time departureTime = rs.getTime("heure_depart");
	             Time ArrivalTime = rs.getTime("heure_arrivee");
	             int id_station_arrivee = rs.getInt("id_station_arrivee");
	             int id_station_depart = rs.getInt("id_station_depart");
	            
	             
	             Trajet trajet = new Trajet(id, id_station_arrivee, id_station_depart, departureTime, ArrivalTime);
	             trajets.add(trajet);
	         }
	         tableMetros.setItems(trajets);
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	
}
public ResultSet selection (String req)
{

		try {
			return st.executeQuery(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}



	
}
public void getTime(String departureCity, String arrivalCity, Time departureTime, Label timeLabel)
{
	
    String query = "SELECT heure_arrivee " +
            "FROM trajet " +
            "WHERE id_station_depart IN (SELECT id FROM station WHERE nom = ?) " +
            "AND id_station_arrivee IN (SELECT id FROM station WHERE nom = ?) " +
            "AND heure_depart = ? ";
	  
    PreparedStatement statement;
	try {
		statement = con.prepareStatement(query);
		 statement.setString(1, departureCity);
		    statement.setString(2, arrivalCity);
		    statement.setTime(3, departureTime);
		    ResultSet resultSet = statement.executeQuery();

		    if (resultSet.next()) {
		        Time arrivalTime = resultSet.getTime("heure_arrivee");
		        timeLabel.setText(arrivalTime.toString());
		    } else {
		    	timeLabel.setText("Aucun trajet disponible.");
		    }


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
public int delete(int c)
{
	if(con!=null) {
		String req="delete from trajet where id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(req);
			ps.setInt(1, c);
		
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	
	}
	return 0;
}
}
