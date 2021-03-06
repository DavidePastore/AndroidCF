package it.androidcf.activity;

import it.androidcf.BuildConfig;
import it.androidcf.Constants;
import it.androidcf.R;
import it.androidcf.codicefiscale.CodiceFiscale;
import it.androidcf.database.AndroidCFDB;
import it.androidcf.exceptions.ComuneNonInseritoException;
import it.androidcf.exceptions.ComuneNonTrovatoException;
import it.androidcf.exceptions.SessoNonInseritoException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AndroidCF extends Activity {
	
	private AndroidCFDB database;
	
	private int durataToast = Toast.LENGTH_SHORT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calcola_codice_fiscale);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Item 1");
		menu.add("Item 2");
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
		
		Time now = new Time();
		now.setToNow();
		int anno = now.year;
		int mese = now.month;
		int giorno = now.monthDay;
		datePicker.updateDate(anno, mese, giorno);
		editTextName.setText("");
		editTextCognome.setText("");
		editTextComune.setText("");
		radioGroup.clearCheck();
	}
	
	
	/**
	 * Calcola il valore del codice fiscale.
	 * @param v the View.
	 */
	public void onCalcolaClicked(View v){
		database = new AndroidCFDB(this);
		
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
			
			if(comuneDiNascita.equals("")){
				throw new ComuneNonInseritoException("Comune non inserito");
			}


			mese += 1;
			CodiceFiscale codiceFiscale = new CodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comuneDiNascita, database);
			String codiceFiscaleGenerato = codiceFiscale.calcola();


			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(getString(R.string.codice_fiscale) + ":\n" + Html.fromHtml("<center>" + codiceFiscaleGenerato + "</center>"))
			       .setCancelable(false)
			       .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                //do things
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
			
			
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Codice fiscale generato: " + codiceFiscaleGenerato);
			}
			
		} catch (SessoNonInseritoException e) {
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Sesso non selezionato.");
			}
			
			Toast toast = Toast.makeText(getApplicationContext(), R.string.sesso_non_selezionato, durataToast);
			toast.show();
		} catch (ComuneNonInseritoException e) {
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Comune non inserito.");
			}
			
			Toast toast = Toast.makeText(getApplicationContext(), R.string.comune_non_inserito, durataToast);
			toast.show();
		} catch (ComuneNonTrovatoException e) {
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Comune non trovato.");
			}
			
			Toast toast = Toast.makeText(getApplicationContext(), R.string.comune_non_trovato, durataToast);
			toast.show();
		} catch (Exception e) {
			if(BuildConfig.DEBUG){
				Log.d(Constants.LOG, "Errore: " + e);
			}
			
			Toast toast = Toast.makeText(getApplicationContext(), R.string.errore_generico, durataToast);
			toast.show();
		}
	}

}
