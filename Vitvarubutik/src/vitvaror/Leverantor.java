package vitvaror;

public class Leverantor {
	private int levid;
	private String namn;
	private int telefonr;
	private String adress;
	public Leverantor(int levid, String namn, int telefonr, String adress) {
		
		this.levid = levid;
		this.namn = namn;
		this.telefonr = telefonr;
		this.adress = adress;
	}
	public int getLevid() {
		return levid;
	}
	public void setLevid(int levid) {
		this.levid = levid;
	}
	public String getNamn() {
		return namn;
	}
	public void setNamn(String namn) {
		this.namn = namn;
	}
	public int getTelefonr() {
		return telefonr;
	}
	public void setTelefonr(int telefonr) {
		this.telefonr = telefonr;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
}
