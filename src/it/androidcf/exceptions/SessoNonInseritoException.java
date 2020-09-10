/**
 * 
 */
package it.androidcf.exceptions;

/**
 * Eccezione: il sesso non è stato inserito.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class SessoNonInseritoException extends Exception {
	
	/**
	 * Costruttore dell'eccezione con relativo messaggio.
	 * @param message Il messaggio dell'eccezione.
	 */
	public SessoNonInseritoException(String message){
		super(message);
	}

}
