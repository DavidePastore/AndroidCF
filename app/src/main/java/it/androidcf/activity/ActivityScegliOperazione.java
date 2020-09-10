package it.androidcf.activity;

import it.androidcf.R;
import it.androidcf.R.layout;
import it.androidcf.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ActivityScegliOperazione extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scegli_operazione);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_scegli_operazione, menu);
		return true;
	}
	
	/**
	 * Passa il controllo all'altra activity.
	 * @param v the View.
	 */
	public void onCalcolaClicked(View v){
		Intent intent = new Intent(this, AndroidCF.class);
        startActivity(intent);
	}

}
