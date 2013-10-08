package it.androidcf.ui;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import it.androidcf.BuildConfig;
import it.androidcf.Constants;
import it.androidcf.R;
import it.androidcf.codicefiscale.CodiceFiscale;

public class AndroidCF extends Activity {
	
	private it.androidcf.database.AndroidCF database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		String dataDiNascita;
		String sesso;
		String comuneDiNascita;
		
		EditText editTextName = (EditText) findViewById(R.id.editTextNome);
		EditText editTextCognome = (EditText) findViewById(R.id.editTextCognome);
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		RadioButton radioButton;
		EditText editTextComune = (EditText) findViewById(R.id.editTextComune);
		
		if(radioGroup.getCheckedRadioButtonId() == -1){
			//Mostra un alert (data non selezionata)
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Data non selezionata -> default: M");
			}
			
			radioButton = (RadioButton) findViewById(R.id.radioM);
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

		try {
			CodiceFiscale codiceFiscale = new CodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comuneDiNascita);
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Codice fiscale generato: " + codiceFiscale.calcola());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
