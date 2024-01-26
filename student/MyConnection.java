package student;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	static Connection connection;
	
	public static Connection getMyConnection() throws ClassNotFoundException, SQLException {
		if(connection == null ) {
			//register driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish Connection
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management_system","root","root");
			return connection;
			
		}
		else
		{
			return connection;
		}
	}

}



