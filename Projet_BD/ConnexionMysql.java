import javax.swing.*;
import java.sql.*;

public class ConnexionMysql {
	Connection conn=null;
	
	public static Connection ConnectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation?serverTimezone=UTC","root","");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
			
		}
	}
	

}
