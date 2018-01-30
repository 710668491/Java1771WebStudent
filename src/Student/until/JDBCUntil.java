package Student.until;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletContext;

public class JDBCUntil {
	/*private static String className ;
	private static String url ;
	private static String user ;
	private static String password;*/
	private static String className = "com.mysql.jdbc.Driver" ;
	private static String url = "jdbc:mysql://localhost:3306/java1711" ;
	private static String user = "root" ;
	private static String password = "root";

	private JDBCUntil() {
	}	
	
	public static void init(ServletContext servletContext) {
		InputStream inputStream;
		try {
			inputStream = servletContext.getResourceAsStream("/WEB-INF/classes/aaa");
			//inputStream = new FileInputStream("aaa");
			Properties properties = new Properties();
			properties.load(inputStream);
			className = properties.getProperty("className");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
	}


	static {
		
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		return DriverManager.getConnection(url, user, password);
		
		
	}

	public static void close(Connection connection, Statement statement ) {
	
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection connection, Statement statement ,ResultSet resultSet ) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
