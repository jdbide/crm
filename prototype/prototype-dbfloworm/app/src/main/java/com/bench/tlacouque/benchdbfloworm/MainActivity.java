package com.bench.tlacouque.benchdbfloworm;

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

import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.ColumnAlias;
import com.raizlabs.android.dbflow.sql.language.Join;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Long start = System.nanoTime();
        initDBFlow();
       /** List<ClientModel> clientModelList = new Select()
                .from(ClientModel.class)
                .join(EtablissementModel.class, Join.JoinType.INNER).as("E")
                .on(Condition.column(ClientModel$Table.ETABLISSEMENT_CLIENT_ETABLISSEMENT_ID)
                        .is(ColumnAlias.columnWithTable("E", EtablissementModel$Table.ID)))
                        .where(Condition.column(ColumnAlias.columnWithTable("E", EtablissementModel$Table.ID)).is(1))
                        .queryList();
        */

        List<ClientModel> clientModelList = new Select()
                .from(ClientModel.class)
                .where(Condition.column(ClientModel$Table.ETABLISSEMENT_CLIENT_ETABLISSEMENT_ID).eq(5))
                .queryList();

        Log.d("Temps requête", "" + String.valueOf(System.nanoTime() - start));
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

    public void initDBFlow() {
        List<ClientModel> clientModelList = new Select().from(ClientModel.class).queryList();
        if(clientModelList == null){
            HashMap<Integer, EtablissementModel> etablissementModelHashMap = initEtablissement();
            initClient(etablissementModelHashMap);
        }
    }

    public HashMap<Integer,EtablissementModel> initEtablissement() {
        HashMap<Integer,EtablissementModel> etablissementModels = new HashMap<>();

        for(int i = 0; i<10; i++) {
            EtablissementModel etablissementModel = new EtablissementModel();
            etablissementModel.setId(i);
            etablissementModel.setName("etablissement "+i);
            etablissementModel.save();
            etablissementModels.put(Integer.valueOf(i),etablissementModel);
        }
        return etablissementModels;
    }

    public void initClient(HashMap<Integer,EtablissementModel> etablissementModels) {
        int j = 0;
        for(int i = 0; i < 999; i++) {
            j++;

            ClientModel clientModel = new ClientModel();
            clientModel.setId(i);
            clientModel.setName("nom " + i);
            clientModel.setFirstname("prenom " + i);
            clientModel.setEtablissement(etablissementModels.get(Integer.valueOf(j)));
            clientModel.save();

            if(j == 9) {
                j = -1;
            }
        }
    }
}
