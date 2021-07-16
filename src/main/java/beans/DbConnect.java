package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

	private static DbConnect connect;
	private Connection connection;
	private static final String adress = "jdbc:mysql://localhost:3306/emargement_en_ligne?characterEncoding=utf8";
	private static final String id = "root";
	private static final String password = "";
	
	
	private DbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(adress,id,password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		}
	
	public static Connection getConnector() throws SQLException {
		
		if (connect == null || connect.getConnection().isClosed())
			connect = new DbConnect();
		else 
			connect = new DbConnect();
		
		return connect.getConnection();
	
	}
	
	private Connection getConnection() {
		return connection;
	}
	
	public void setConection(Connection connection) {
		this.connection = connection;
	}
	
}