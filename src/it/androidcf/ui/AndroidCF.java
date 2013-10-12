package it.androidcf.ui;

import it.androidcf.BuildConfig;
import it.androidcf.Constants;
import it.androidcf.R;
import it.androidcf.codicefiscale.CodiceFiscale;
import it.androidcf.exceptions.ComuneNonInseritoException;
import it.androidcf.exceptions.ComuneNonTrovatoException;
import it.androidcf.exceptions.SessoNonInseritoException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AndroidCF extends Activity {
	
	private it.androidcf.database.AndroidCF database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calcola_codice_fiscale);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	/**
	 * Resetta i valori dei campi
	 * @param v the View.
	 */
	public void onBtnClicked(View v){
		EditText editTextName = (EditText) findViewById(R.id.editTextNome);
		EditText editTextCognome = (EditText) findViewById(R.id.editTextCognome);
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		EditText editTextComune = (EditText) findViewById(R.id.editTextComune);
		
		
		editTextName.setText("");
		editTextCognome.setText("");
		editTextComune.setText("");
		radioGroup.clearCheck();
	}
	
	
	/**
	 * Calcola il valore del comune.
	 * @param v the View.
	 */
	public void onCalcolaClicked(View v){
		database = new it.androidcf.database.AndroidCF(this);
		
		String nome;
		String cognome;
		int giorno;
		int mese;
		int anno;
		String sesso;
		String comuneDiNascita;

		try {
			EditText editTextName = (EditText) findViewById(R.id.editTextNome);
			EditText editTextCognome = (EditText) findViewById(R.id.editTextCognome);
			DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
			RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
			RadioButton radioButton;
			EditText editTextComune = (EditText) findViewById(R.id.editTextComune);
			
			if(radioGroup.getCheckedRadioButtonId() == -1){
				throw new SessoNonInseritoException("Sesso non selezionato");
			}
			else{
				radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
			}
			
			nome = editTextName.getText().toString();
			cognome = editTextCognome.getText().toString();
			sesso = (String) radioButton.getText();
			giorno = datePicker.getDayOfMonth();
			mese = datePicker.getMonth();
			anno = datePicker.getYear();
			comuneDiNascita = editTextComune.getText().toString();
			
			if(comuneDiNascita.isEmpty()){
				throw new ComuneNonInseritoException("Comune non inserito");
			}
			
			
			CodiceFiscale codiceFiscale = new CodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comuneDiNascita, database);
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Codice fiscale generato: " + codiceFiscale.calcola());
			}
			
		} catch (SessoNonInseritoException e) {
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Sesso non selezionato.");
			}
		} catch (ComuneNonInseritoException e) {
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Comune non inserito.");
			}
		} catch (ComuneNonTrovatoException e) {
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Comune non trovato.");
			}
		} catch (Exception e) {
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Errore: " + e);
			}
		}
	}

}
