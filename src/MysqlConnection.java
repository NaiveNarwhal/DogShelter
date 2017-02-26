import java.sql.*;

public class MysqlConnection {
	
	
	public static Connection databaseConnect(){
		try {
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dog_shelter?verifyServerCertificate=false&useSSL=true", "root", "database77");
		    return connection;
		   
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    return null;
		}
	}
	

}
