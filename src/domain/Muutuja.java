package domain;


public class Muutuja extends Valem {
	/*
	 * klass valemitele, mis ei sisalda ¸ühtegi tehet, vaid sisaldavad täpselt ühte muutujat
	 */
	private boolean toevaartus = false;
	private String nimi;
	
	public Muutuja(String nimi) {
		super();

		this.nimi = nimi;
		
		this.muutujad.add(this);
	}
	
	public String getNimi() {
		return nimi;
	}

	public void setToevaartus(boolean toevaartus) {
		this.toevaartus = toevaartus;
	}

	@Override
	public boolean vaartus() {
		return toevaartus;
	}

}