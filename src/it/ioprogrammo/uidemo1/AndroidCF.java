package it.ioprogrammo.uidemo1;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AndroidCF extends Activity {
	
	private it.ioprogrammo.database.AndroidCF database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		database = new it.ioprogrammo.database.AndroidCF(this);
		Cursor cursor = database.getCodiciCatastali();
		System.out.println("Elementi: "+cursor.getCount());
		
		//while(cursor.moveToNext()){
			System.out.println("ID: " + cursor.getInt(it.ioprogrammo.database.AndroidCF.COLONNA_ID) + "; NomeComune: " + cursor.getString(it.ioprogrammo.database.AndroidCF.COLONNA_NOME_COMUNE));
		//}/**/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	/**
	 * Reset the value of the fields
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

}
