package pizza_reservation;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
	
	public static Connection getConnection() throws Exception {
		Properties props = new Properties();
		try (FileInputStream file = new FileInputStream("conf.properties")) {
			props.load(file);
		}
		
		Class.forName( props.getProperty("jdbc.driver.class") );
		String url      = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		
		Connection connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}
}


