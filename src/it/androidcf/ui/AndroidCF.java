package it.androidcf.ui;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import it.androidcf.R;

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
		EditText editTextComune = (EditText) findViewById(R.id.editTextComune);
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		
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
		
		EditText editTextComune = (EditText) findViewById(R.id.editTextComune);

		String codiceCatastale;
		try {
			codiceCatastale = database.getCodiceCatastale(editTextComune.getText().toString().toUpperCase());
			System.out.println("Codice catastale: " + codiceCatastale);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
