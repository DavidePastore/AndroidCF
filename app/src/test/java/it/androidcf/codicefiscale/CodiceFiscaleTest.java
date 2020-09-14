package it.androidcf.codicefiscale;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import it.androidcf.database.AndroidCFDB;

@RunWith(RobolectricTestRunner.class)
public class CodiceFiscaleTest {

    Context instrumentationContext;

    @Before
    public void setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().getContext();
    }

    private AndroidCFDB getAndroidCFDB() {
        return new AndroidCFDB(instrumentationContext);
    }

    @Test
    public void testCalcolaCodiceFiscale() throws Exception {
        String nome = "Mario";
        String cognome = "Rossi";
        int giorno = 10;
        int mese = 12;
        int anno = 1985;
        String sesso = "M";
        String comuneDiNascita = "San Giuliano Terme";
        CodiceFiscale codiceFiscale = new CodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comuneDiNascita, getAndroidCFDB());
        String calculated = codiceFiscale.calcola();
        Assert.assertEquals("RSSMRA85T10A562S", calculated);
    }

    @Test
    public void testCalcolaCodiceFiscaleWithApostropheInTheName() throws Exception {
        String nome = "M'ario";
        String cognome = "Rossi";
        int giorno = 10;
        int mese = 12;
        int anno = 1985;
        String sesso = "M";
        String comuneDiNascita = "San Giuliano Terme";
        CodiceFiscale codiceFiscale = new CodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comuneDiNascita, getAndroidCFDB());
        String calculated = codiceFiscale.calcola();
        Assert.assertEquals("RSSMRA85T10A562S", calculated);
    }

    @Test
    public void testCalcolaCodiceFiscaleWithApostropheInTheSurname() throws Exception {
        String nome = "Mario";
        String cognome = "R'ossi";
        int giorno = 10;
        int mese = 12;
        int anno = 1985;
        String sesso = "M";
        String comuneDiNascita = "San Giuliano Terme";
        CodiceFiscale codiceFiscale = new CodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comuneDiNascita, getAndroidCFDB());
        String calculated = codiceFiscale.calcola();
        Assert.assertEquals("RSSMRA85T10A562S", calculated);
    }
}
