package it.androidcf.database;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NoteSelectionActivity extends ListActivity {
/*
	private DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new DatabaseHelper(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(TableComuni.TABLE_NAME, new String[] {
				TableComuni.COLUMN_ID, TableComuni.COLUMN_TITLE }, null, null,
				null, null, TableComuni.COLUMN_ID + " ASC");
		setListAdapter(new CursorAdapter(this, cursor, true) {

			@Override
			public View newView(Context context, Cursor cursor, ViewGroup parent) {
				TextView textView = new TextView(NoteSelectionActivity.this);
				textView.setText(cursor.getString(1));
				return textView;
			}

			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				TextView textView = (TextView) view;
				textView.setText(cursor.getString(1));
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbHelper.close();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		setResult(1, new Intent(String.valueOf(id)));
		finish();
	}
*/
}
