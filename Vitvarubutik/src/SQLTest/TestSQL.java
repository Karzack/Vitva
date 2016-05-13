package SQLTest;

import java.sql.*;

public class TestSQL {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private String user, password;

	public TestSQL(String user, String password) {
		establishConnection();
		this.user = user;
		this.password = password;

	}

	public void establishConnection() {
	
		try {
			conn = DriverManager.getConnection("jdbc:mysql://ddwap.mah.se:3306/af9292", user, password);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestSQL ts = new TestSQL("AF****", "*******");
	}
}
