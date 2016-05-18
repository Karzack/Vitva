package vitvaror;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
	private static final String DATABASE_USER = "AF2015";
	private static final String DATABASE_PASS = "mongo";
	
	private Connection connection;

	public DatabaseHandler() {
		initConnection();
	}

	private void initConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://ddwap.mah.se:3306/af2015", DATABASE_USER, DATABASE_PASS);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) { 
			e.printStackTrace();
		}
	}

	public void addCustomer(String namn, String email, String telefonnr, String adress) throws SQLException {
        PreparedStatement query = connection.prepareStatement( "INSERT INTO kund(namn, email, telefonnr, adress) values (?, ?, ?, ?)");
        query.setString(1, namn);
        query.setString(2, email);
        query.setString(3, telefonnr);
        query.setString(4, adress);
        query.executeUpdate();
    }
	
	public ArrayList<Kund> getCustomers() throws SQLException {
        PreparedStatement query = connection.prepareStatement("SELECT * FROM kund");
        ResultSet rs = query.executeQuery();
        ArrayList<Kund> resList = new ArrayList<Kund>();
        while (rs.next()) {
            Kund row = new Kund(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            resList.add(row);
        }
        
        return resList;
    }
	
	public void addArticle(String namn, String tillverkare, String modell, String energiklass, String beskrivning, int pris, int inkopspris, int antal) throws SQLException {
        PreparedStatement query = connection.prepareStatement( "INSERT INTO artikel(namn, tillverkare, modell, energiklass, beskrivning, pris, inköpspris, antal) values (?, ?, ?, ?, ?, ?, ?, ?)");
        query.setString(1, namn);
        query.setString(2, tillverkare);
        query.setString(3, modell);
        query.setString(4, energiklass);
        query.setString(5, beskrivning);
        query.setInt(6, pris);
        query.setInt(7, inkopspris);
        query.setInt(8, antal);
        query.executeUpdate();
    }
	
	public static void main(String[] args) throws SQLException {
		DatabaseHandler handler = new DatabaseHandler();
		//handler.addCustomer("Alexander Johansson", "alexanderjoh94@gmail.com", "0707320788", "Södra Förstadsgatan 132");
		//handler.addArticle("Logitech G35", "Logitech", "G35", "", "Headset", 1000, 700, 150);
		
		ArrayList<Kund> customerList = handler.getCustomers();
		for(Kund customer : customerList) {
			System.out.println(customer.getNamn() + " " + customer.getKundId());
		}
	}
}
