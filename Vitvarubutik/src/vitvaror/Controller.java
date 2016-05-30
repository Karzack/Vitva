package vitvaror;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
	private KundUI kundUI;
	private LeverantorUI leverantorUI;
	private OrderUI orderUI;
	private ArtikelUI artikelUI;

	private DatabaseHandler databaseHandler;

	public Controller(int choice, int id) throws SQLException {
		switch (choice) {
		case 1:
			databaseHandler = new DatabaseHandler();

			leverantorUI = new LeverantorUI(this);
			leverantorUI.setVisible(true);
			leverantorUI.setLocation(450, 300);

			kundUI = new KundUI(this);
			kundUI.setVisible(true);
			kundUI.setLocation(0, 300);

			artikelUI = new ArtikelUI(this);
			artikelUI.setVisible(true);
			artikelUI.setLocation(0, 0);

			orderUI = new OrderUI(this, 1, id);
			orderUI.setVisible(true);
			orderUI.setLocation(900, 0);
			break;

		case 2:
			
			databaseHandler = new DatabaseHandler();
			orderUI = new OrderUI(this, 2, id);
			orderUI.setVisible(true);
			orderUI.setLocation(900, 0);
			

			break;
		}

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

	public void updateCustomer(int kundid, String namn, String email, String telefonnr, String adress)
			throws SQLException {
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

	public void deleteArticle(Artikel artikel) throws SQLException {
		databaseHandler.removeArtikel(artikel.getArtikelId());
	}

	public void updateArticle(int artikelId, String namn, String tillverkare, String modell, String energiklass,
			String beskrivning, int pris, int inkopspris, int antal) throws SQLException {
		databaseHandler.updateArticle(artikelId, namn, tillverkare, modell, energiklass, beskrivning, pris, inkopspris,
				antal);
	}

	public void addArticle(String namn, String tillverkare, String modell, String energiklass, String beskrivning,
			int pris, int inkopspris, int antal) throws SQLException {
		databaseHandler.addArticle(namn, tillverkare, modell, energiklass, beskrivning, pris, inkopspris, antal);
	}

	public void deleteDeliverer(Leverantor leverantor) throws SQLException {
		databaseHandler.removeDeliverer(leverantor.getLevid());
	}

	public void addDeliverer(String namn, String telefonnr, String adress) throws SQLException {
		databaseHandler.addDeliverer(namn, telefonnr, adress);
	}

	public void updateDeliverer(int levid, String namn, String telefonnr, String adress) throws SQLException {
		databaseHandler.updateDeliverer(levid, namn, telefonnr, adress);
	}

	public Artikel getArticle(int artikelId) throws SQLException {

		return databaseHandler.getArticle(artikelId);

	}

	public Kund getCustomer(int kundId) throws SQLException {

		
		return databaseHandler.getCustomer(kundId);

	}
}
