
package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class userDAO {
	Connection con= null;
	Statement st=null;
	private Alert alert1;
	
public userDAO() {
	con=MaConnexion.connecter();
	if(con!=null)
	{
		try {
			st=con.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
public int insert(String ft,String st,String userN,String pass,String email,String type)
{
	if(con!=null) {
		String req="insert into users (firstName,lastName,userName,password,email,type)values(?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(req);
			ps.setString(1, ft);
			ps.setString(2,st);
			ps.setString(3,userN);
			ps.setString(4, pass);
			ps.setString(5,email);
			ps.setString(6, type);
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




public void verifyUser(String nameUser,String password,String type,Stage stage ) 
{
	 System.out.println(type);
	ResultSet rs=null;
	try {
		rs = st.executeQuery("select * from users where userName='"+nameUser+"'and password='"+password+"' and type='"+type+"'");
		if(!rs.next())
		{ 
		  
			alert1=new Alert(AlertType.ERROR);
			alert1.setTitle("Error");
			alert1.setHeaderText("Error Message");
			alert1.setContentText("Verify your data");
			alert1.show();
		}
		else
		{
         if(type=="Admin")
         {
   		  stage.close();

          // Load the FXML file for the dashboard
		  StackPane root;
			try {
				root = (StackPane)FXMLLoader.load(getClass().getResource("AdminUi.fxml"));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         else
        	 if(type=="User")
         {
        		 
        		  System.out.println("c'est un user");
        
 
        		  
        		  stage.close();

                    // Load the FXML file for the dashboard
        		  StackPane root;
					try {
					
					      
					      FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
					        Parent root1 = loader.load();
					        MainController m = loader.getController();
					        
					    
					        m.init(nameUser);
						  Rectangle rectangle = new Rectangle(885.0, 555);
		        	        rectangle.setArcWidth(30);
		        	        rectangle.setArcHeight(30);
		        	        
		        			root1.setClip(rectangle);
		        			Scene scene = new Scene(root1,885.0,555.0,Color.TRANSPARENT);
		        			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		        			Stage primaryStage=new Stage();
		    				primaryStage.initStyle(StageStyle.TRANSPARENT);
		        			primaryStage.setScene(scene);
		        			primaryStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                 


        	      
         }
         
		}
	} catch (SQLException e) {
		System.out.print("no");

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
public int delete(int c)
{
	if(con!=null) {
		String req="delete from users where id=?";
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
