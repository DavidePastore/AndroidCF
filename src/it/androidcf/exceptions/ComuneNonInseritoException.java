/**
 * 
 */
package it.androidcf.exceptions;

/**
 * Eccezione: il comune non è stato inserito.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class ComuneNonInseritoException extends Exception {
	
	/**
	 * Costruttore dell'eccezione con relativo messaggio.
	 * @param message Il messaggio dell'eccezione.
	 */
	public ComuneNonInseritoException(String message){
		super(message);
	}

}
