package vitvaror;

import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
	private KundUI kundUI;
	
	private DatabaseHandler databaseHandler;
	
	public Controller() throws SQLException {
		databaseHandler = new DatabaseHandler();
		
		kundUI = new KundUI(this);
		kundUI.setVisible(true);
	}
	
	public ArrayList<Kund> getCustomers() throws SQLException {
		return databaseHandler.getCustomers();
	}
	
	public void deleteCustomer(Kund kund) throws SQLException {
		databaseHandler.removeCustomer(kund.getKundId());
	}
	
	public void updateCustomer(int kundid, String namn, String email, String telefonnr, String adress) throws SQLException {
		databaseHandler.updateCustomer(kundid, namn, email, telefonnr, adress);
	}

	public void addCustomer(String namn, String email, String telefonnr, String adress) throws SQLException {
		databaseHandler.addCustomer(namn, email, telefonnr, adress);
	}
	
	public static void main(String[] args) throws SQLException {
		Controller controller = new Controller();
	}
}
