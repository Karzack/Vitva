package vitvaror;

import java.util.ArrayList;

public class Order {
	private int orderId;
	private int kundId;
	
	private ArrayList<Artikel> artiklar;

	public Order(int orderId, int kundId, ArrayList<Artikel> artiklar) {
		this.orderId = orderId;
		this.kundId = kundId;
		this.artiklar = artiklar;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getKundId() {
		return kundId;
	}

	public void setKundId(int kundId) {
		this.kundId = kundId;
	}

	public ArrayList<Artikel> getArtiklar() {
		return artiklar;
	}

	public void setArtiklar(ArrayList<Artikel> artiklar) {
		this.artiklar = artiklar;
	}
}
