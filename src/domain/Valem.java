package domain;

import java.util.HashSet;

public abstract class Valem {
	/* 
	 * abstraktne klass ümbritsemaks kõiki objekte, millel saab tõeväärtust arvutada
	 * muutujad on kõigi valemis leiduvate muutujate hulk
	 */
	public HashSet<Muutuja> muutujad;
	public abstract boolean vaartus();
	
	Valem() {
		this.muutujad = new HashSet<Muutuja>();
	}

	@Override
	public String toString() {
		return this.vaartus() ? "t" : "v";
	}
}