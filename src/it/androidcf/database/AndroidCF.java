package it.androidcf.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class AndroidCF extends SQLiteAssetHelper{

	private static final String DATABASE_NAME = "AndroidCF";
    private static final int DATABASE_VERSION = 1;
    
    //Colonne
    public static final int COLONNA_ID = 0;
    public static final int COLONNA_NOME_COMUNE = 1;
    public static final int COLONNA_CODICE_CATASTALE = 2;
    public static final int COLONNA_PROVINCIA = 3;

    public AndroidCF(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
    
    
    /**
     * Restituisce tutti i codici catastali.
     * @return
     */
    public Cursor getCodiciCatastali() {

    	SQLiteDatabase db = getReadableDatabase();
    	SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

    	String [] sqlSelect = {"Codicecatastale"};  //_Id", "NomeComune"};
    	String sqlTables = "CodiciCatastali";
    	String selection = "Nomecomune = ?";
    	String[] selectionArgs = new String[]{
    			"TRICASE"
    	};

    	qb.setTables(sqlTables);
    	Cursor c = qb.query(db, null, selection, selectionArgs,
    			null, null, null);

    	c.moveToFirst();
    	return c;

    }
    
    /**
     * Restituisce il codice catastale di un comune.
     * @param comune il comune del quale prendere il
     * @return Restituisce il CodiceCatastale.
     * @throws Exception 
     */
    public String getCodiceCatastale(String comune) throws Exception {
    	
    	SQLiteDatabase db = getReadableDatabase();
    	SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

    	String [] projection = {"Codicecatastale"};
    	String sqlTables = "CodiciCatastali";
    	String selection = "Nomecomune = ?";
    	String[] selectionArgs = {
    			comune
    	};

    	qb.setTables(sqlTables);
    	Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, null);

    	c.moveToFirst();
    	
    	if(c.getCount() == 0){
    		throw new Exception("Nessun codice catastale trovato");
    	}
    	
    	return c.getString(0);
    }

}
