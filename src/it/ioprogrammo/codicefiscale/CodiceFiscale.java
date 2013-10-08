package it.ioprogrammo.codicefiscale;

import it.ioprogrammo.util.UtilsLettere;

/**
 * La classe che rappresenta il codice fiscale.
 * @author <a href="https://github.com/DavidePastore">Davide Pastore</a>
 *
 */
public class CodiceFiscale {
	
	private String nome;
	private String cognome;
	private String dataDiNascita;
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
	public CodiceFiscale(String nome, String cognome, String dataDiNascita, String sesso, String comuneDiNascita){
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
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
	 * @return the dataDiNascita
	 */
	public String getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * @param dataDiNascita the dataDiNascita to set
	 */
	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
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
	 * Calcola il cognome del codice fiscale.
	 * @param cognome il cognome da cui calcolare il valore
	 * @return Il cognome nel formato voluto dal codice fiscale.
	 */
	private String calcolaCognome(String cognome){
		String cognomeCalcolato;
		int numeroConsonanti;
		cognome = UtilsLettere.eliminaSpaziBianchi(cognome);
		
		if(cognome.length() >= 3){
			numeroConsonanti = UtilsLettere.getNumeroConsonanti(cognome);
			
			if(numeroConsonanti >= 3){
				cognomeCalcolato = UtilsLettere.getPrimeConsonanti(cognome, 3);
			}
			else{
				cognomeCalcolato = UtilsLettere.getPrimeConsonanti(cognome, numeroConsonanti);
				cognomeCalcolato += UtilsLettere.getPrimeVocali(cognome, 3 - numeroConsonanti);
			}
		}
		else{
			int numeroCaratteri = cognome.length();
			cognomeCalcolato = cognome + UtilsLettere.nXChar(3 - numeroCaratteri);
		}
		
		
		return cognomeCalcolato;
	}
}
