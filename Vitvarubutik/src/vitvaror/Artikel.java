package vitvaror;

public class Artikel {
	private int artikelId;
	
	private String namn;
	private String tillverkare;
	private String modell;
	private String energiklass;
	private String beskrivning;
	
	private int pris;
	private int inkopspris;
	private int antal;

	public Artikel(int artikelId, String namn, String tillverkare, String modell, String energiklass, String beskrivning, int pris, int inkopspris, int antal) {
		this.artikelId = artikelId;
		this.namn = namn;
		this.tillverkare = tillverkare;
		this.modell = modell;
		this.energiklass = energiklass;
		this.beskrivning = beskrivning;
		this.pris = pris;
		this.inkopspris = inkopspris;
		this.antal = antal;
	}
	
	public int getArtikelId() {
		return artikelId;
	}

	public void setArtikelId(int artikelId) {
		this.artikelId = artikelId;
	}

	public String getNamn() {
		return namn;
	}

	public void setNamn(String namn) {
		this.namn = namn;
	}

	public String getTillverkare() {
		return tillverkare;
	}

	public void setTillverkare(String tillverkare) {
		this.tillverkare = tillverkare;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public String getEnergiklass() {
		return energiklass;
	}

	public void setEnergiklass(String energiklass) {
		this.energiklass = energiklass;
	}

	public String getBeskrivning() {
		return beskrivning;
	}

	public void setBeskrivning(String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public int getPris() {
		return pris;
	}

	public void setPris(int pris) {
		this.pris = pris;
	}

	public int getInkopspris() {
		return inkopspris;
	}

	public void setInkopspris(int inkopspris) {
		this.inkopspris = inkopspris;
	}

	public int getAntal() {
		return antal;
	}

	public void setAntal(int antal) {
		this.antal = antal;
	}
	
	public String toString() {
		return "Artikel (" + this.artikelId + "): " + this.namn + " - " + this.pris;
	}
}
