package it.androidcf.util;

/**
 * Utilità per le lettere.
 * @author <a href="https://github.com/DavidePastore">Davide Pastore</a>
 *
 */
public class UtilsLettere {
	
	/**
	 * Tutte le vocali.
	 */
	private static final String VOCALI = "AEIOU";
	
	/**
	 * Calcola il numero delle consonanti presenti nella stringa.
	 * @param string la stringa sulla quale calcolare le consonanti.
	 * @return Restituisce il numero delle consonanti.
	 */
	public static int getNumeroConsonanti(String string){
		int consonanti = 0;
		string = string.toUpperCase();
		for(int i = 0; i < string.length(); i++){
			if(!VOCALI.contains(Character.toString(string.charAt(i)))){
				consonanti++;
			}
		}
		return consonanti;
	}
	
	
	/**
	 * Restituisce le prime numero consonanti presenti nella stringa.
	 * @param string la stringa sulla quale prelevare le consonanti.
	 * @param numero il numero di consonanti da prelevare.
	 * @return Restituisce le prime numero consonanti.
	 */
	public static String getPrimeConsonanti(String string, int numero){
		String consonanti = "";
		string = string.toUpperCase();
		for(int i = 0; i < string.length(); i++){
			if(!VOCALI.contains(Character.toString(string.charAt(i)))){
				if(numero < consonanti.length()){
					consonanti += Character.toString(string.charAt(i));
				}
			}
		}
		return consonanti;
	}
	
	/**
	 * Restituisce le prime numero vocali presenti nella stringa.
	 * @param string la stringa sulla quale prelevare le vocali.
	 * @param numero il numero di vocali da prelevare.
	 * @return Restituisce le prime numero vocali.
	 */
	public static String getPrimeVocali(String string, int numero){
		String vocali = "";
		string = string.toUpperCase();
		for(int i = 0; i < string.length(); i++){
			if(VOCALI.contains(Character.toString(string.charAt(i)))){
				if(numero < vocali.length()){
					vocali += Character.toString(string.charAt(i));
				}
			}
		}
		return vocali;
	}
	
	/**
	 * Restituisce una stringa di n X.
	 * @param n il numero di X.
	 * @return Restituisce la stringa con n X.
	 */
	public static String nXChar(int n){
		String risultato = "";
		for(int i = 0; i < n; i++){
			risultato += "X";
		}
		return risultato;
	}
	
	
	/**
	 * Elimina tutti gli spazi bianchi presenti nella stringa.
	 * @param string la stringa sulla quale eliminare gli spazi bianchi.
	 * @return Restituisce la stringa senza gli spazi bianchi.
	 * @see http://stackoverflow.com/a/5455809
	 */
	public static String eliminaSpaziBianchi(String string){
		return string.replaceAll("\\s+","");
	}
	
	
	/**
	 * Controlla se un carattere è una vocale.
	 * @param character il carattere.
	 * @return Restituisce true se il carattere una vocale.
	 */
	public static boolean isVocale(char character){
		return VOCALI.contains(Character.toString(character));
	}
	
	

}
