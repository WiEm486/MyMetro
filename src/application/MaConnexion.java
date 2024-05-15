package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {

	public static Connection connecter(){
		Connection con = null;
		try {
			String drivername = "com.mysql.jdbc.Driver";
			Class.forName(drivername);
			 con= DriverManager.getConnection("jdbc:mysql://localhost/userbd", "root","");
				System.out.print("succes");
		} catch (SQLException e) {
			System.out.print("erreur de connexion");
			e.printStackTrace();
		}
        catch(ClassNotFoundException e)
		{
        	//System.out.print("erreur de drive");
        	e.printStackTrace();
		}
		return con;
	}
	
	
}
