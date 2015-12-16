package com.bench.tlacouque.benchormandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initSugarORM();
        Long start = System.nanoTime();
        List<ClientSugarORM> clientTest = ClientSugarORM.find(ClientSugarORM.class, "etablissement = ?",String.valueOf(5));
        Log.d("Temps requête",""+String.valueOf(System.nanoTime()-start));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Initialise SugarORM au premier lancement de l'application
    public void initSugarORM() {
        List<ClientSugarORM> clientTest = ClientSugarORM.find(ClientSugarORM.class, "nom = ?", "test");
        if (clientTest == null) {
            initEtablissement();
            initClient();

        }
    }

    public void initClient() {
        int j = 0;
        for (int i = 0; i < 999; i++) {

            j++;
            ClientSugarORM client = new ClientSugarORM(i, "test " + i, "test " + i, "etablissement " + i);
            client.save();

            if (j == 10) {
                j = -1;
            }
        }
    }

    public void initEtablissement() {

        for (int i = 0; i < 11; i++) {
            EtablissementSugarORM etablissementSugarORM = new EtablissementSugarORM(i,"etablissement " + i);
            etablissementSugarORM.save();
        }
    }


}