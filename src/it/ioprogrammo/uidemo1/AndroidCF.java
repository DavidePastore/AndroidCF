package it.ioprogrammo.uidemo1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AndroidCF extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView label1 = new TextView(this);
		label1.setText(getString(R.string.nome) + ":");
		EditText edit1 = new EditText(this);
		TextView label2 = new TextView(this);
		label2.setText(getString(R.string.cognome) + ":");
		EditText edit2 = new EditText(this);
		TextView label3 = new TextView(this);
		label3.setText(getString(R.string.sesso) + ":");
		RadioButton radio1 = new RadioButton(this);
		radio1.setText(getString(R.string.maschio));
		RadioButton radio2 = new RadioButton(this);
		radio2.setText(getString(R.string.femmina));
		RadioGroup radioGroup1 = new RadioGroup(this);
		radioGroup1.setOrientation(LinearLayout.HORIZONTAL);
		radioGroup1.setGravity(Gravity.CENTER);
		radioGroup1.addView(radio1);
		radioGroup1.addView(radio2);
		Button button1 = new Button(this);
		button1.setText(getString(R.string.salva));
		Button button2 = new Button(this);
		button2.setText(getString(R.string.annulla));
		TableRow row1 = new TableRow(this);
		row1.setGravity(Gravity.CENTER);
		row1.addView(label1);
		edit1.setGravity(Gravity.FILL);
		row1.addView(edit1);
		TableRow row2 = new TableRow(this);
		row2.setGravity(Gravity.CENTER);
		row2.addView(label2);
		row2.addView(edit2);
		TableRow row3 = new TableRow(this);
		row3.setGravity(Gravity.CENTER);
		row3.addView(label3);
		row3.addView(radioGroup1);
		TableLayout tableLayout = new TableLayout(this);
		tableLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
		tableLayout.addView(row1);
		tableLayout.addView(row2);
		tableLayout.addView(row3);
		tableLayout.setColumnShrinkable(0, true);
		tableLayout.setColumnStretchable(1, true);
		LinearLayout linearLayout1 = new LinearLayout(this);
		linearLayout1.setGravity(Gravity.CENTER);
		linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
		linearLayout1.addView(button1);
		linearLayout1.addView(button2);
		LinearLayout linearLayout2 = new LinearLayout(this);
		linearLayout2.setGravity(Gravity.CENTER);
		linearLayout2.setOrientation(LinearLayout.VERTICAL);
		linearLayout2.setPadding(5, 5, 5, 5);
		linearLayout2.addView(tableLayout);
		linearLayout2.addView(linearLayout1);
		setContentView(linearLayout2);
	}

}