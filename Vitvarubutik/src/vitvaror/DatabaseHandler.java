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
		PreparedStatement query = connection.prepareStatement("SELECT * FROM kund WHERE kundid=?");
		query.setInt(1, kundId);
		ResultSet rs = query.executeQuery();
		rs.next();
		return new Kund(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
	}
	
	public void removeCustomer(int kundId) throws SQLException {
		PreparedStatement query = connection.prepareStatement("DELETE FROM kund WHERE kundid=?");
		query.setInt(1, kundId);
		query.executeUpdate();
	}
	
	public ArrayList<Artikel> getArticles() throws SQLException {
		PreparedStatement query = connection.prepareStatement("SELECT * FROM artikel");
		ResultSet rs = query.executeQuery();
		ArrayList<Artikel> resList = new ArrayList<Artikel>();
		while (rs.next()) {
			Artikel row = new Artikel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			resList.add(row);
		}

		return resList;
	}
	
	public Artikel getArticle(int artikelId) throws SQLException{
		PreparedStatement query = connection.prepareStatement("SELECT * FROM artikel WHERE artikelid = ?");
		query.setInt(1, artikelId);
		ResultSet rs = query.executeQuery();
		rs.next();
		return new Artikel(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9));
	}
	
	public void addArticle(String namn, String tillverkare, String modell, String energiklass, String beskrivning, int pris, int inkopspris, int antal) throws SQLException {
		PreparedStatement query = connection.prepareStatement( "INSERT INTO artikel(namn, tillverkare, modell, energiklass, beskrivning, pris, inkopspris, antal) values (?, ?, ?, ?, ?, ?, ?, ?)");
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
		PreparedStatement query = connection.prepareStatement( "UPDATE kund SET namn=?, tillverkare=?, modell=?, energiklass=?, beskrivning=?, pris=?, inkopspris=?, antal=? WHERE artikelid=?");
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
	
	public int addOrder(int kundId, long tid) throws SQLException {
		PreparedStatement query = connection.prepareStatement( "INSERT INTO af2015.order(kundid, tid) values (?, ?)");
		query.setInt(1, kundId);
		query.setLong(2, tid);
		query.executeUpdate();
		
		PreparedStatement query2 = connection.prepareStatement("SELECT orderid FROM af2015.order WHERE kundid=? AND tid=?");
		query2.setInt(1, kundId);
		query2.setLong(2, tid);
		ResultSet rs = query2.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	public int addOrderrad(int orderId) throws SQLException {
		PreparedStatement query = connection.prepareStatement( "INSERT INTO af2015.orderrad values (null, ?)");
		query.setInt(1, orderId);
		query.executeUpdate();
		
		PreparedStatement query2 = connection.prepareStatement("SELECT orderradid FROM af2015.orderrad WHERE orderid=?");
		query2.setInt(1, orderId);
		ResultSet rs = query2.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	public void addArticleToOrderrad(int orderradId, Artikel artikel, int antal) throws SQLException {
		PreparedStatement query = connection.prepareStatement( "INSERT INTO af2015.har values (?, ?, ?)");
		query.setInt(1, orderradId);
		query.setInt(2, artikel.getArtikelId());
		query.setInt(3, antal);
		query.executeUpdate();
	}

	public ArrayList<Leverantor> getDeliverers() throws SQLException {
		PreparedStatement query = connection.prepareStatement("SELECT * FROM leverantor");
		ResultSet rs = query.executeQuery();
		ArrayList<Leverantor> resList = new ArrayList<Leverantor>();
		while (rs.next()) {
			Leverantor row = new Leverantor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			resList.add(row);
		}

		return resList;
	}

	public void updateDeliverer(int levid, String namn, String telefonnr, String adress) throws SQLException {
		PreparedStatement query = connection.prepareStatement( "UPDATE leverantor SET namn=?, telefonnr=?, adress=? WHERE leverantorid=?");
		query.setString(1, namn);
		query.setString(2, telefonnr);
		query.setString(3, adress);
		query.setInt(4, levid);
		query.executeUpdate();
	}

	public void addDeliverer(String namn, String telefonnr, String adress) throws SQLException {
		PreparedStatement query = connection.prepareStatement( "INSERT INTO leverantor(namn, telefonnr, adress) values (?, ?, ?)");
		query.setString(1, namn);
		query.setString(2, telefonnr);
		query.setString(3, adress);
		query.executeUpdate();
	}
	
	public void removeDeliverer(int levid) throws SQLException {
		PreparedStatement query = connection.prepareStatement("DELETE FROM leverantor WHERE leverantorid=?");
		query.setInt(1, levid);
		query.executeUpdate();
	}

	public void removeArtikel(int artikelID) throws SQLException {
		PreparedStatement query = connection.prepareStatement("DELETE FROM artikel WHERE artikelid=?");
		query.setInt(1, artikelID);
		query.executeUpdate();
	}
	


}
