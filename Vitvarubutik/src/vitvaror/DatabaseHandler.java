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

	public int getCustomerIdByName(String namn) throws SQLException {
		PreparedStatement query = connection.prepareStatement("SELECT kundid FROM kund WHERE namn=?");
		query.setString(1, namn);
		ResultSet rs = query.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

	public void updateCustomer(int kundid, String namn, String email, String telefonnr, String adress) throws SQLException {
		PreparedStatement query = connection.prepareStatement( "UPDATE kund SET namn=?, email=?, telefonnr=?, adress=? WHERE kundid=?");
		query.setString(1, namn);
		query.setString(2, email);
		query.setString(3, telefonnr);
		query.setString(4, adress);
		query.setInt(5, kundid);
		query.executeUpdate();
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

	public Kund getCustomer(int kundId) throws SQLException {
		PreparedStatement query = connection.prepareStatement("SELECT * FROM kund where kundid=?");
		query.setInt(1, kundId);
		ResultSet rs = query.executeQuery();
		rs.next();
		return new Kund(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
	}
	
	public Artikel getArticle(int artikelId) throws SQLException{
		PreparedStatement query = connection.prepareStatement("SELECT * FROM artikel where artikelid = ?");
		query.setInt(1, artikelId);
		ResultSet rs = query.executeQuery();
		rs.next();
		return new Artikel(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9));
	}
	
	public void addArticle(String namn, String tillverkare, String modell, String energiklass, String beskrivning, int pris, int inkopspris, int antal) throws SQLException {
		PreparedStatement query = connection.prepareStatement( "INSERT INTO artikel(namn, tillverkare, modell, energiklass, beskrivning, pris, ink�pspris, antal) values (?, ?, ?, ?, ?, ?, ?, ?)");
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
	
	public void updateArticle(int artikelId, String namn, String tillverkare, String modell, String energiklass, String beskrivning, int pris, int inkopspris, int antal) throws SQLException {
		PreparedStatement query = connection.prepareStatement( "UPDATE kund SET namn=?, tillverkare=?, modell=?, energiklass=?, beskrivning=?, pris=?, ink�pspris=?, antal=? WHERE artikelid=?");
		query.setString(1, namn);
        query.setString(2, tillverkare);
        query.setString(3, modell);
        query.setString(4, energiklass);
        query.setString(5, beskrivning);
        query.setInt(6, pris);
        query.setInt(7, inkopspris);
        query.setInt(8, antal);
        query.setInt(9, artikelId);
        query.executeUpdate();
	}

	public static void main(String[] args) throws SQLException {
		DatabaseHandler handler = new DatabaseHandler();
		//handler.addCustomer("Alexander Johansson", "alexanderjoh94@gmail.com", "0707320788", "S�dra F�rstadsgatan 132");
		//handler.addArticle("Logitech G35", "Logitech", "G35", "", "Headset", 1000, 700, 150);

		ArrayList<Kund> customerList = handler.getCustomers();
		for(Kund customer : customerList) {
			System.out.println(customer.getNamn() + " " + customer.getKundId());
		}
	}
}
