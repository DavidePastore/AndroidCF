package it.androidcf.codicefiscale;

import android.util.Log;
import it.androidcf.BuildConfig;
import it.androidcf.Constants;
import it.androidcf.util.UtilsLettere;

/**
 * La classe che rappresenta il codice fiscale.
 * @author <a href="https://github.com/DavidePastore">Davide Pastore</a>
 *
 */
public class CodiceFiscale {
	
	private String nome;
	private String cognome;
	private int giorno;
	private int mese;
	private int anno;
	private String sesso;
	private String comuneDiNascita;
	private String carattereDiControllo;
	
	/**
	 * Costruttore della classe
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param sesso
	 * @param comuneDiNascita
	 */
	public CodiceFiscale(String nome, String cognome, int giorno, int mese, int anno, String sesso, String comuneDiNascita){
		this.nome = nome;
		this.cognome = cognome;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
		this.sesso = sesso;
		this.comuneDiNascita = comuneDiNascita;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	

	/**
	 * @return the giorno
	 */
	public int getGiorno() {
		return giorno;
	}

	/**
	 * @param giorno the giorno to set
	 */
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	/**
	 * @return the mese
	 */
	public int getMese() {
		return mese;
	}

	/**
	 * @param mese the mese to set
	 */
	public void setMese(int mese) {
		this.mese = mese;
	}

	/**
	 * @return the anno
	 */
	public int getAnno() {
		return anno;
	}

	/**
	 * @param anno the anno to set
	 */
	public void setAnno(int anno) {
		this.anno = anno;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the comuneDiNascita
	 */
	public String getComuneDiNascita() {
		return comuneDiNascita;
	}

	/**
	 * @param comuneDiNascita the comuneDiNascita to set
	 */
	public void setComuneDiNascita(String comuneDiNascita) {
		this.comuneDiNascita = comuneDiNascita;
	}

	/**
	 * @return the carattereDiControllo
	 */
	public String getCarattereDiControllo() {
		return carattereDiControllo;
	}
	
	
	/**
	 * Calcola il codice fiscale.
	 * @return Restituisce il codice fiscale generato.
	 */
	public String calcola(){
		String cognomeCalcolato = this.calcolaCognome(cognome);
		String risultato = cognomeCalcolato;
		
		
		return risultato;
	}


	
	/**
	 * Calcola il cognome del codice fiscale.
	 * @param cognome il cognome da cui calcolare il valore
	 * @return Il cognome nel formato voluto dal codice fiscale.
	 */
	private String calcolaCognome(String cognome){
		String cognomeCalcolato;
		int numeroConsonanti;
		cognome = UtilsLettere.eliminaSpaziBianchi(cognome).toUpperCase();
		
		if(cognome.length() >= 3){
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il cognome >= 3");
			}
			numeroConsonanti = UtilsLettere.getNumeroConsonanti(cognome);
			
			if(numeroConsonanti >= 3){
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc cognome >= 3");
				}
				cognomeCalcolato = UtilsLettere.getPrimeConsonanti(cognome, 3);
			}
			else{
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc cognome < 3");
				}
				cognomeCalcolato = UtilsLettere.getPrimeConsonanti(cognome, numeroConsonanti);
				cognomeCalcolato += UtilsLettere.getPrimeVocali(cognome, 3 - numeroConsonanti);
			}
		}
		else{
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il cognome < 3");
			}
			int numeroCaratteri = cognome.length();
			cognomeCalcolato = cognome + UtilsLettere.nXChar(3 - numeroCaratteri);
		}
		
		
		return cognomeCalcolato;
	}
	
	private String calcolaComune(String comune){
		//it.androidcf.database.AndroidCF database = new it.androidcf.database.AndroidCF(this);
		//return database.getCodiceCatastale(editTextComune.getText().toString().toUpperCase());
		return "";
	}
}
