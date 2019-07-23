package domain;

import java.util.HashSet;

public class Toevaartus extends Valem {
	/*
	 * klass valemitele, mis ei sisalda enam ühtegi tehet ega muutujat
	 */
	private boolean toevaartus;
	
	public Toevaartus(boolean toevaartus) {
		super();
		this.toevaartus = toevaartus;
	}
	
	@Override
	public boolean vaartus() {
		return toevaartus;
	}

}