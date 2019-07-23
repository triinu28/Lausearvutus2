package exceptions;

public class EbakorrektneValemException extends Exception {
	//Erand, mis visatakse, kui valem ei ole korrektne lausearvutuse valem.

	public EbakorrektneValemException() {
	}

	public EbakorrektneValemException(String message) {
		super(message);
	}

}
