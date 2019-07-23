package domain;

public class Eitus extends Valem {
	/*
	 * klass valemitele, mille viimasena teostatavaks tehteks on eitus
	 */
	private Valem argument;
	
	public Eitus(Valem argument) {
		super();
		this.argument = argument;
		
		this.muutujad.addAll(argument.muutujad);
	}

	@Override
	public boolean vaartus() {
		return !argument.vaartus();
	}

}
