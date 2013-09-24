package it.ioprogrammo.uidemo1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AndroidCF extends Activity {

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
	 * Reset the value of the fields
	 * @param v
	 */
	public void onBtnClicked(View v){
		EditText editTextName = (EditText) findViewById(R.id.editTextNome);
		EditText editTextCognome = (EditText) findViewById(R.id.editTextCognome);
		EditText editTextComune = (EditText) findViewById(R.id.editTextComune);
		editTextName.setText("");
		editTextCognome.setText("");
		editTextComune.setText("");
	}

}
