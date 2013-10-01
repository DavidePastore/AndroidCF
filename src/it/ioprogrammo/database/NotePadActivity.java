package it.ioprogrammo.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotePadActivity extends Activity {
	/*
	private static final int MENU_ITEM_NEW = 1;
	private static final int MENU_ITEM_LOAD = 2;
	private static final int MENU_ITEM_SAVE = 3;
	private static final int MENU_ITEM_DELETE = 4;

	private static final int DIALOG_SAVE = 1;

	private static final int REQUEST_NOTE_SELECTION = 1;

	private DatabaseHelper dbHelper;

	private EditText contentsEditor;
	private EditText titleEditor;

	private Long currentNodeId = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new DatabaseHelper(this);
		setContentView(R.layout.main);
		contentsEditor = (EditText) findViewById(R.id.contentsEditor);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbHelper.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, MENU_ITEM_NEW, 0, "Nuova");
		menu.add(Menu.NONE, MENU_ITEM_LOAD, 1, "Carica");
		menu.add(Menu.NONE, MENU_ITEM_SAVE, 2, "Salva");
		menu.add(Menu.NONE, MENU_ITEM_DELETE, 3, "Cancella");
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		for (int i = 0; i < menu.size(); i++) {
			MenuItem item = menu.getItem(i);
			if (item.getItemId() == MENU_ITEM_DELETE) {
				if (currentNodeId == null) {
					item.setEnabled(false);
				} else {
					item.setEnabled(true);
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ITEM_LOAD:
			startActivityForResult(
					new Intent(this, NoteSelectionActivity.class),
					REQUEST_NOTE_SELECTION);
			return true;
		case MENU_ITEM_SAVE:
			if (currentNodeId == null) {
				showDialog(DIALOG_SAVE);
			} else {
				updateNote();
			}
			return true;
		case MENU_ITEM_DELETE:
			if (currentNodeId != null) {
				deleteNote();
			}
			return true;
		case MENU_ITEM_NEW:
			currentNodeId = null;
			contentsEditor.setText("");
			return true;
		default:
			return false;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == DIALOG_SAVE) {
			LayoutInflater inflater = getLayoutInflater();
			View customView = inflater.inflate(R.layout.savedialog, null);
			titleEditor = (EditText) customView.findViewById(R.id.titleEditor);
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setCancelable(true);
			builder.setTitle("Salva nota");
			builder.setView(customView);
			builder.setPositiveButton("Salva", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					createNote();
				}
			});
			builder.setNegativeButton("Annulla", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			return builder.create();
		}
		return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		if (id == DIALOG_SAVE) {
			titleEditor.setText("");
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_NOTE_SELECTION) {
			if (resultCode == 1) {
				long id = Long.parseLong(data.getAction());
				loadNote(id);
			}
		}
	}

	private void loadNote(long id) {
		String where = TableComuni.COLUMN_ID + " = " + id;
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = null;
		try {
			cursor = db.query(TableComuni.TABLE_NAME,
					new String[] { TableComuni.COLUMN_CONTENTS }, where, null,
					null, null, null);
			if (cursor.moveToNext()) {
				String contents = cursor.getString(0);
				contentsEditor.setText(contents);
				currentNodeId = id;
				alert("Nota caricata");
			} else {
				alert("Nota non trovata");
			}
		} catch (Exception e) {
			Log.e("NotePadActivity", "Errore nel salvataggio della nota", e);
			alert("Errore nel salvataggio della nota");
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	private void createNote() {
		String title = titleEditor.getText().toString().trim();
		if (title.length() == 0) {
			alert("Titolo non specificato!");
			return;
		}
		String contents = contentsEditor.getText().toString().trim();
		if (contents.length() == 0) {
			alert("Contenuto vuoto!");
			return;
		}
		ContentValues values = new ContentValues();
		values.put(TableComuni.COLUMN_TITLE, title);
		values.put(TableComuni.COLUMN_CONTENTS, contents);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			currentNodeId = db.insertOrThrow(TableComuni.TABLE_NAME, null, values);
			alert("Nota creata!");
		} catch (Exception e) {
			Log.e("NotePadActivity", "Errore nel salvataggio della nota", e);
			alert("Errore nel salvataggio della nota");
		}
	}

	private void updateNote() {
		String contents = contentsEditor.getText().toString().trim();
		if (contents.length() == 0) {
			alert("Contenuto vuoto!");
			return;
		}
		ContentValues values = new ContentValues();
		values.put(TableComuni.COLUMN_CONTENTS, contents);
		String where = TableComuni.COLUMN_ID + " = " + currentNodeId;
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			db.update(TableComuni.TABLE_NAME, values, where, null);
			alert("Nota salvata!");
		} catch (Exception e) {
			Log.e("NotePadActivity", "Errore nel salvataggio della nota", e);
			alert("Errore nel salvataggio della nota");
		}
	}

	private void deleteNote() {
		String contents = contentsEditor.getText().toString().trim();
		if (contents.length() == 0) {
			alert("Contenuto vuoto!");
			return;
		}
		ContentValues values = new ContentValues();
		values.put(TableComuni.COLUMN_CONTENTS, contents);
		String where = TableComuni.COLUMN_ID + " = " + currentNodeId;
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			db.delete(TableComuni.TABLE_NAME, where, null);
			currentNodeId = null;
			contentsEditor.setText("");
			alert("Nota eliminata!");
		} catch (Exception e) {
			Log.e("NotePadActivity", "Errore nel cancellare la nota", e);
			alert("Errore nel cancellare la nota");
		}
	}

	private void alert(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	*/
}