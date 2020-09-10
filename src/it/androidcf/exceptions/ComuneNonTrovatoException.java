/**
 * 
 */
package it.androidcf.exceptions;

/**
 * Eccezione: il comune non è stato trovato.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class ComuneNonTrovatoException extends Exception{
	
	/**
	 * Costruttore dell'eccezione con relativo messaggio.
	 * @param message Il messaggio dell'eccezione.
	 */
	public ComuneNonTrovatoException(String message){
		super(message);
	}

}
