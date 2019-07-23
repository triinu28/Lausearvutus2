package domain;

public class Ekvivalents extends Valem {
	/*
	 * klass valemitele, mille viimasena teostatavaks tehteks on ekvivalents
	 */
	private Valem vasak;
	private Valem parem;
	
	public Ekvivalents(Valem vasak, Valem parem) {
		super();
		this.vasak = vasak;
		this.parem = parem;
		
		this.muutujad.addAll(vasak.muutujad);
		this.muutujad.addAll(parem.muutujad);
	}

	@Override
	public boolean vaartus() {
		return vasak.vaartus() == parem.vaartus();
	}

}