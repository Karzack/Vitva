package vitvaror;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
	private KundUI kundUI;
	private LeverantorUI leverantorUI;
	private OrderUI orderUI;
	
	private DatabaseHandler databaseHandler;
	
	public Controller() throws SQLException {
		databaseHandler = new DatabaseHandler();
		
		leverantorUI = new LeverantorUI(this);
		leverantorUI.setVisible(true);
		
		kundUI = new KundUI(this);
		kundUI.setVisible(true);
		
		orderUI = new OrderUI(this);
		orderUI.setVisible(true);
	}
	
	public ArrayList<Kund> getCustomers() throws SQLException {
		return databaseHandler.getCustomers();
	}
	
	public ArrayList<Artikel> getArticles() throws SQLException {
		return databaseHandler.getArticles();
	}
	
	public ArrayList<Leverantor> getDeliverers() throws SQLException {
		return databaseHandler.getDeliverers();
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
	
	public int addOrder(int kundId, long tid) throws SQLException {
		return databaseHandler.addOrder(kundId, tid);
	}
	
	public int addOrderrad(int orderId) throws SQLException {
		return databaseHandler.addOrderrad(orderId);
	}
	
	public void addArticleToOrderrad(int orderradId, Artikel artikel, int antal) throws SQLException {
		databaseHandler.addArticleToOrderrad(orderradId, artikel, antal);
	}
	
	public static void main(String[] args) throws SQLException {
		Controller controller = new Controller();
	}

	public void deleteDeliverer(Leverantor leverantor) throws SQLException {
		databaseHandler.removeDeliverer(leverantor.getLevid());
	}

	
}
