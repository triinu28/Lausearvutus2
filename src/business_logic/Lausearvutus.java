package business_logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import domain.Disjunktsioon;
import domain.Eitus;
import domain.Ekvivalents;
import domain.Implikatsioon;
import domain.Konjunktsioon;
import domain.Muutuja;
import domain.Toevaartus;
import domain.Valem;
import exceptions.EbakorrektneValemException;

public class Lausearvutus {
	public static List<Muutuja> koikMuutujad;
	
	private static Valem soelu(String sis) throws EbakorrektneValemException {
		/*
		 * rekursiivselt valemite sõelumiseks
		 * http://eki.ee/dict/ies/index.cgi?Q=parse
		 */
		int sulgusid = 0;
		for (int i = 0; i < sis.length(); i++) {
			if (sis.charAt(i) == '(') sulgusid++;
			if (sis.charAt(i) == ')') sulgusid--;
			if (sulgusid<0) throw new EbakorrektneValemException();
			if (sis.charAt(i) == '~' && sulgusid == 0) {
				Valem vasak = soelu(sis.substring(0, i));
				Valem parem = soelu(sis.substring(i+1, sis.length()));
				return new Ekvivalents(vasak, parem);
			}
		}
		if (sulgusid!=0) throw new EbakorrektneValemException();
		for (int i = 0; i < sis.length(); i++) {
			if (sis.charAt(i) == '(') sulgusid++;
			if (sis.charAt(i) == ')') sulgusid--;
			if (sis.charAt(i) == '>' && sulgusid == 0) {
				Valem vasak = soelu(sis.substring(0, i));
				Valem parem = soelu(sis.substring(i+1, sis.length()));
				return new Implikatsioon(vasak, parem);
			}
		}
		for (int i = 0; i < sis.length(); i++) {
			if (sis.charAt(i) == '(') sulgusid++;
			if (sis.charAt(i) == ')') sulgusid--;
			if (sis.charAt(i) == '|' && sulgusid == 0) {
				Valem vasak = soelu(sis.substring(0, i));
				Valem parem = soelu(sis.substring(i+1, sis.length()));
				return new Disjunktsioon(vasak, parem);
			}
		}
		for (int i = 0; i < sis.length(); i++) {
			if (sis.charAt(i) == '(') sulgusid++;
			if (sis.charAt(i) == ')') sulgusid--;
			if (sis.charAt(i) == '&' && sulgusid == 0) {
				Valem vasak = soelu(sis.substring(0, i));
				Valem parem = soelu(sis.substring(i+1, sis.length()));
				return new Konjunktsioon(vasak, parem);
			}
		}
		if (sis.charAt(0) == '!') {
			Valem argument = soelu(sis.substring(1, sis.length()));
			return new Eitus(argument);
		}
		if (sis.charAt(0) == 'v' && sis.length()==1) {
			return new Toevaartus(false);
		}
		if (sis.charAt(0) == 't' && sis.length()==1) {
			return new Toevaartus(true);
		}
		if (sis.charAt(0) == '(' && sis.charAt(sis.length()-1) == ')') {
			return soelu(sis.substring(1, sis.length()-1));
		}
		if (sis.contains("(") || sis.contains(")") 
				|| sis.contains("v") || sis.contains("t") || sis.contains("!")) {
			throw new EbakorrektneValemException();
		}
		for (Muutuja m : koikMuutujad){
			if (m.getNimi().equals(sis)) return m;
		}
		Muutuja m = new Muutuja(sis);
		koikMuutujad.add(m);
		return m;
	}
	
	public static void tabel(HashSet<Muutuja> muutujad, Valem valem) {
		/*
		 * funktsioon, mis kuvab ekraanil tõeväärtustabeli
		 */
		Iterator<Muutuja> i = muutujad.iterator();
		Muutuja muutuja = i.next();
		
		HashSet<Muutuja> uuedMuutujad = new HashSet<Muutuja>();
		uuedMuutujad.addAll(muutujad);
		uuedMuutujad.remove(muutuja);
		
		for (boolean vaartus: new boolean[]{true, false}) {
			muutuja.setToevaartus(vaartus);
			if (!uuedMuutujad.isEmpty()) {
				tabel(uuedMuutujad, valem);
			} else {
				for (Muutuja m : valem.muutujad) {
					System.out.print(m + " ");
				}
				System.out.println(": " + valem);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// t v ! & | > ~
		System.out.println("Lausearvutaja v0.1\n"
				+ "  S¸mbolid:\n"
				+ "    t - tõde\n"
				+ "    v - vale\n"
				+ "    ! - eitus\n"
				+ "    & - konjunktsioon\n"
				+ "    | - disjunktsioon\n"
				+ "    > - implikatsioon\n"
				+ "    ~ - ekvivalents\n"
				+ "\n"
				+ "Programm arvutab välja lause väärtuse, kui see ei sisalda muutujaid, ning väljastab tõeväärtustabeli muutujate esinemisel.\n"
				+ "    q - välju programmist");
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.print("> ");
			koikMuutujad = new ArrayList<Muutuja>();
			String sisend = stdin.readLine().trim();
			if (sisend.equals("q")) {
				break;
			}
			try{
				Valem valem = soelu(sisend);
				if (valem.muutujad.isEmpty()) {
					/*
					 * lause ei sisalda muutujaid; tuleb kuvada ekraanil selle väärtus
					 */
					System.out.println(sisend);
					System.out.println(valem);
				} else {
					/*
					 * lause sisaldab muutujaid; tuleb kuvada ekraanil tõeväärtustabel
					 */
					for (Muutuja m : valem.muutujad) {
						System.out.print(m.getNimi() + " ");
					}
					System.out.println(sisend);
					tabel(valem.muutujad, valem);
				}
			}
			catch(StringIndexOutOfBoundsException e) { 
				System.out.println("Ebakorrektne sisend, proovi uuesti");
			}
			catch(EbakorrektneValemException e) { 
				System.out.println("Ebakorrektne sisend, proovi uuesti");
			}
			
		
		}
	}

}
