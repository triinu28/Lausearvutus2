package domain;

public class Konjunktsioon extends Valem {
	/*
	 * klass valemitele, mille viimasena teostatavaks tehteks on konjunktsioon
	 */
	private Valem vasak;
	private Valem parem;
	
	public Konjunktsioon(Valem vasak, Valem parem) {
		super();
		this.vasak = vasak;
		this.parem = parem;
		
		this.muutujad.addAll(vasak.muutujad);
		this.muutujad.addAll(parem.muutujad);
	}
	
	@Override
	public boolean vaartus() {
		return vasak.vaartus() && parem.vaartus();
	}
}