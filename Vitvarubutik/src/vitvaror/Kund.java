package vitvaror;

public class Kund {
	private int kundId;
	
	private String namn;
	private String email;
	private String telefonnr;
	private String adress;
	
	public Kund(int kundId, String namn, String email, String telefonnr, String adress) {
		this.kundId = kundId;
		this.namn = namn;
		this.email = email;
		this.telefonnr = telefonnr;
		this.adress = adress;
	}
	
	public int getKundId() {
		return kundId;
	}
	
	public void setKundId(int kundId) {
		this.kundId = kundId;
	}
	
	public String getNamn() {
		return namn;
	}
	
	public void setNamn(String namn) {
		this.namn = namn;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefonnr() {
		return telefonnr;
	}
	
	public void setTelefonnr(String telefonnr) {
		this.telefonnr = telefonnr;
	}
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}
}
