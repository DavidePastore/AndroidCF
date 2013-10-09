package it.androidcf.codicefiscale;

import android.util.Log;
import it.androidcf.BuildConfig;
import it.androidcf.Constants;
import it.androidcf.ui.AndroidCF;
import it.androidcf.util.UtilsParole;

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
	private it.androidcf.database.AndroidCF database;
	
	/**
	 * Costruttore della classe
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param sesso
	 * @param comuneDiNascita
	 * @param database 
	 */
	public CodiceFiscale(String nome, String cognome, int giorno, int mese, int anno, String sesso, String comuneDiNascita, it.androidcf.database.AndroidCF androidCF){
		this.nome = nome;
		this.cognome = cognome;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
		this.sesso = sesso;
		this.comuneDiNascita = comuneDiNascita;
		this.database = androidCF;
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
	 * @throws Exception 
	 */
	public String calcola() throws Exception{
		String codiceCognome = this.calcolaCodiceCognome(this.cognome);
		String codiceNome = this.calcolaCodiceNome(this.nome);
		String codiceDataNascitaESesso = this.calcolaCodiceDataNascitaESesso(this.anno, this.mese, this.giorno, this.sesso);
		String codiceComunale = this.calcolaCodiceComune(this.comuneDiNascita);
		
		String risultato = codiceCognome + codiceNome + codiceDataNascitaESesso + codiceComunale;
		
		String carattereDiControllo = this.calcolaCarattereDiControllo(risultato);
		
		risultato += carattereDiControllo;
		
		return risultato;
	}


	/**
	 * Calcola il codice del cognome del codice fiscale.
	 * @param cognome il cognome da cui calcolare il codice
	 * @return Il codice del cognome del codice fiscale.
	 */
	private String calcolaCodiceCognome(String cognome){
		String codiceCognome;
		int numeroConsonanti;
		cognome = UtilsParole.eliminaSpaziBianchi(cognome).toUpperCase();
		
		if(cognome.length() >= 3){
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il cognome >= 3");
			}
			numeroConsonanti = UtilsParole.getNumeroConsonanti(cognome);
			
			if(numeroConsonanti >= 3){
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc cognome >= 3");
				}
				codiceCognome = UtilsParole.getPrimeConsonanti(cognome, 3);
			}
			else{
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc cognome < 3");
				}
				codiceCognome = UtilsParole.getPrimeConsonanti(cognome, numeroConsonanti);
				codiceCognome += UtilsParole.getPrimeVocali(cognome, 3 - numeroConsonanti);
			}
		}
		else{
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il cognome < 3");
			}
			int numeroCaratteri = cognome.length();
			codiceCognome = cognome + UtilsParole.nXChar(3 - numeroCaratteri);
		}
		
		
		return codiceCognome;
	}
	
	
	/**
	 * Calcola il codice del nome del codice fiscale.
	 * @param nome il nome da cui calcolare il codice
	 * @return Il codice del nome del codice fiscale.
	 */
	private String calcolaCodiceNome(String nome){
		String codiceNome;
		int numeroConsonanti;
		nome = UtilsParole.eliminaSpaziBianchi(nome).toUpperCase();
		
		if(nome.length() >= 3){
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il nome >= 3");
			}
			numeroConsonanti = UtilsParole.getNumeroConsonanti(nome);
			
			if(numeroConsonanti >= 4){
				Log.d(Constants.LOG, "nc nome >= 4");
				codiceNome = UtilsParole.getConsonanteI(nome, 1) + UtilsParole.getConsonanteI(nome, 3) + UtilsParole.getConsonanteI(nome, 4);
			}
			else if(numeroConsonanti >= 3){
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc nome >= 3");
				}
				codiceNome = UtilsParole.getPrimeConsonanti(nome, 3);
			}
			else{
				if(BuildConfig.DEBUG){
					Log.d(Constants.LOG, "nc nome < 3");
				}
				codiceNome = UtilsParole.getPrimeConsonanti(nome, numeroConsonanti);
				codiceNome += UtilsParole.getPrimeVocali(nome, 3 - numeroConsonanti);
			}
		}
		else{
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Il nome < 3");
			}
			int numeroCaratteri = nome.length();
			codiceNome = nome + UtilsParole.nXChar(3 - numeroCaratteri);
		}
		
		
		return codiceNome;
	}
	
	
	/**
	 * Calcola il codice della data di nascita e del sesso.
	 * @param anno l'anno da cui calcolare il codice.
	 * @param mese il mese da cui calcolare il codice.
	 * @param giorno il giorno da cui calcolare il codice.
	 * @param sesso il sesso da cui calcolare il codice.
	 * @return Il codice della data di nascita e del sesso del codice fiscale.
	 */
	private String calcolaCodiceDataNascitaESesso(int anno, int mese, int giorno, String sesso){
		String codiceDataNascitaESesso;
		String codiceAnno;
		String codiceMese;
		String codiceGiornoESesso;
		
		codiceAnno = calcolaCodiceAnno(anno);
		codiceMese = calcolaCodiceMese(mese);
		codiceGiornoESesso = calcolaCodiceGiornoESesso(giorno, sesso);
		
		codiceDataNascitaESesso = codiceAnno + codiceMese + codiceGiornoESesso;
		
		return codiceDataNascitaESesso;
	}
	
	/**
	 * Calcola il codice dell'anno.
	 * @param anno l'anno da cui calcolare il codice.
	 * @return Il codice dell'anno del codice fiscale.
	 */
	private String calcolaCodiceAnno(int anno){
		return Integer.toString(anno).substring(2);
	}
	
	/**
	 * Calcola il codice del mese.
	 * @param mese il mese da cui calcolare il codice.
	 * @return Il codice del mese del codice fiscale.
	 */
	private String calcolaCodiceMese(int mese){
		String risultato;
		mese++; //I mesi iniziano da 1
		switch(mese){
			case 1:
				risultato = "A";
				break;
			case 2:
				risultato = "B";
				break;
			case 3:
				risultato = "C";
				break;
			case 4:
				risultato = "D";
				break;
			case 5:
				risultato = "E";
				break;
			case 6:
				risultato = "H";
				break;
			case 7:
				risultato = "L";
				break;
			case 8:
				risultato = "M";
				break;
			case 9:
				risultato = "P";
				break;
			case 10:
				risultato = "R";
				break;
			case 11:
				risultato = "S";
				break;
			case 12:
				risultato = "T";
				break;
			default:
				risultato = "";
				break;
		}
		return risultato;
	}
	
	
	/**
	 * Calcola il codice del giorno e del sesso.
	 * @param giorno il giorno da cui calcolare il codice.
	 * @param sesso il sesso da cui calcolare il codice.
	 * @return Il codice del giorno e del sesso del codice fiscale.
	 */
	private String calcolaCodiceGiornoESesso(int giorno, String sesso){
		String codiceGiorno = String.format("%02d", giorno);
		
		if(sesso.equals("F")){
			int codiceGiornoIntero;
			codiceGiornoIntero = Integer.parseInt(codiceGiorno);
			codiceGiornoIntero += 40;
			codiceGiorno = Integer.toString(codiceGiornoIntero);
		}
		
		return codiceGiorno;
	}
	
	
	/**
	 * Calcola il codice del comune.
	 * @param comune il comune da cui calcolare il codice.
	 * @return Il codice del comune del codice fiscale.
	 * @throws Exception
	 */
	private String calcolaCodiceComune(String comune) throws Exception{
		return database.getCodiceCatastale(comune.toUpperCase());
	}
	
	
	/**
	 * Calcola il codice di controllo.
	 * @param codice il codice fiscale senza l'ultima cifra.
	 * @return Il codice di controllo del codice fiscale.
	 * @throws Exception
	 */
	private String calcolaCarattereDiControllo(String codice) throws Exception{
		
		//Passaggio 1 (suddivisione dispari e pari)
		String pari = UtilsParole.getStringaPari(codice);
		String dispari = UtilsParole.getStringaDispari(codice);
		
		//Passaggio 2 (conversione valori)
		dispari = conversioneCaratteriDispari(dispari);
		pari = conversioneCaratteriPari(pari);
		
		//Passaggio 3 (somma, divisione e conversione finale)
		long somma = Long.parseLong(dispari) + Long.parseLong(pari);
		int resto = (int) somma % 26;
		char restoConvertito = conversioneResto(resto);
		
		if(BuildConfig.DEBUG){
			Log.d(Constants.LOG, "dispari: " + dispari);
			Log.d(Constants.LOG, "pari: " + pari);
			Log.d(Constants.LOG, "somma: " + somma);
			Log.d(Constants.LOG, "resto: " + resto);
			Log.d(Constants.LOG, "restoConvertito: " + restoConvertito);
		}
		
		return Character.toString(restoConvertito);
	}
	
	/**
	 * Conversione dei caratteri dispari per il secondo passaggio della creazione del carattere di controllo.
	 * @param string la stringa dei caratteri dispari.
	 * @return Stringa dispari convertita.
	 */
	private String conversioneCaratteriDispari(String string){
		String risultato = "";
		for(int i = 0; i < string.length(); i++){
			char carattere = string.charAt(i);
			if(carattere == '0'){
				risultato += "1";
			} else if(carattere == '1'){
				risultato += "0";
			} else if(carattere == '2'){
				risultato += "5";
			} else if(carattere == '3'){
				risultato += "7";
			} else if(carattere == '4'){
				risultato += "9";
			} else if(carattere == '5'){
				risultato += "13";
			} else if(carattere == '6'){
				risultato += "15";
			} else if(carattere == '7'){
				risultato += "17";
			} else if(carattere == '8'){
				risultato += "19";
			} else if(carattere == '9'){
				risultato += "21";
			} else if(carattere == 'A'){
				risultato += "1";
			} else if(carattere == 'B'){
				risultato += "0";
			} else if(carattere == 'C'){
				risultato += "5";
			} else if(carattere == 'D'){
				risultato += "7";
			} else if(carattere == 'E'){
				risultato += "9";
			} else if(carattere == 'F'){
				risultato += "13";
			} else if(carattere == 'G'){
				risultato += "15";
			} else if(carattere == 'H'){
				risultato += "17";
			} else if(carattere == 'I'){
				risultato += "19";
			} else if(carattere == 'J'){
				risultato += "21";
			} else if(carattere == 'K'){
				risultato += "2";
			} else if(carattere == 'L'){
				risultato += "4";
			} else if(carattere == 'M'){
				risultato += "18";
			} else if(carattere == 'N'){
				risultato += "20";
			} else if(carattere == 'O'){
				risultato += "11";
			} else if(carattere == 'P'){
				risultato += "3";
			} else if(carattere == 'Q'){
				risultato += "6";
			} else if(carattere == 'R'){
				risultato += "8";
			} else if(carattere == 'S'){
				risultato += "12";
			} else if(carattere == 'T'){
				risultato += "14";
			} else if(carattere == 'U'){
				risultato += "16";
			} else if(carattere == 'V'){
				risultato += "10";
			} else if(carattere == 'W'){
				risultato += "22";
			} else if(carattere == 'X'){
				risultato += "25";
			} else if(carattere == 'Y'){
				risultato += "24";
			} else if(carattere == 'Z'){
				risultato += "23";
			}
		}
		return risultato;
	}
	
	
	/**
	 * Conversione dei caratteri pari per il secondo passaggio della creazione del carattere di controllo.
	 * @param string la stringa dei caratteri pari.
	 * @return Stringa pari convertita.
	 */
	private String conversioneCaratteriPari(String string){
		String risultato = "";
		for(int i = 0; i < string.length(); i++){
			char carattere = string.charAt(i);
			if(carattere == '0'){
				risultato += "0";
			} else if(carattere == '1'){
				risultato += "1";
			} else if(carattere == '2'){
				risultato += "2";
			} else if(carattere == '3'){
				risultato += "3";
			} else if(carattere == '4'){
				risultato += "4";
			} else if(carattere == '5'){
				risultato += "5";
			} else if(carattere == '6'){
				risultato += "6";
			} else if(carattere == '7'){
				risultato += "7";
			} else if(carattere == '8'){
				risultato += "8";
			} else if(carattere == '9'){
				risultato += "9";
			} else if(carattere == 'A'){
				risultato += "0";
			} else if(carattere == 'B'){
				risultato += "1";
			} else if(carattere == 'C'){
				risultato += "2";
			} else if(carattere == 'D'){
				risultato += "3";
			} else if(carattere == 'E'){
				risultato += "4";
			} else if(carattere == 'F'){
				risultato += "5";
			} else if(carattere == 'G'){
				risultato += "6";
			} else if(carattere == 'H'){
				risultato += "7";
			} else if(carattere == 'I'){
				risultato += "8";
			} else if(carattere == 'J'){
				risultato += "9";
			} else if(carattere == 'K'){
				risultato += "10";
			} else if(carattere == 'L'){
				risultato += "11";
			} else if(carattere == 'M'){
				risultato += "12";
			} else if(carattere == 'N'){
				risultato += "13";
			} else if(carattere == 'O'){
				risultato += "14";
			} else if(carattere == 'P'){
				risultato += "15";
			} else if(carattere == 'Q'){
				risultato += "16";
			} else if(carattere == 'R'){
				risultato += "17";
			} else if(carattere == 'S'){
				risultato += "18";
			} else if(carattere == 'T'){
				risultato += "19";
			} else if(carattere == 'U'){
				risultato += "20";
			} else if(carattere == 'V'){
				risultato += "21";
			} else if(carattere == 'W'){
				risultato += "22";
			} else if(carattere == 'X'){
				risultato += "23";
			} else if(carattere == 'Y'){
				risultato += "24";
			} else if(carattere == 'Z'){
				risultato += "25";
			}
		}
		return risultato;
	}

	
	/**
	 * Conversione del resto in un carattere per il terzo passaggio della creazione del carattere di controllo.
	 * @param resto il resto da convertire.
	 * @return Resto convertito.
	 */
	private char conversioneResto(int resto){
		return (char) (resto + 65);
	}


}
