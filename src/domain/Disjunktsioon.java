package domain;

public class Disjunktsioon extends Valem {
	/*
	 * klass valemitele, mille viimasena teostatavaks tehteks on disjunktsioon
	 */
	private Valem vasak;
	private Valem parem;
	
	public Disjunktsioon(Valem vasak, Valem parem) {
		super();
		this.vasak = vasak;
		this.parem = parem;
		
		this.muutujad.addAll(vasak.muutujad);
		this.muutujad.addAll(parem.muutujad);
	}

	@Override
	public boolean vaartus() {
		return vasak.vaartus() || parem.vaartus();
	}

}