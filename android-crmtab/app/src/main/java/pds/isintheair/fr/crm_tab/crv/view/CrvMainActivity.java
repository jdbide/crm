package pds.isintheair.fr.crm_tab.crv.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.crv.mock.RandomInformation;
import pds.isintheair.fr.crm_tab.crv.model.Client;
import pds.isintheair.fr.crm_tab.crv.model.Report;

public class CrvMainActivity extends AppCompatActivity {

    Client client;
    int clientId;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Report> dataList = new ArrayList<Report>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crv_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Report report = new Report();
        report.setId("1");
        report.setClient("5");
        report.setCommercial("1");
        report.setVisit("1");
        report.setComment("Client satisfait");

        dataList.add(report);

        client = (Client)getIntent().getSerializableExtra("ClientObject");
        clientId = client.getClientId();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                RandomInformation mock = new RandomInformation();
                //String json = mock.getRandomInfo();
                Intent intent = new Intent(CrvMainActivity.this, CreateCrvActivity.class);
                //intent.putExtra("mock",json);
                intent.putExtra("ClientObject", client);
                CrvMainActivity.this.startActivity(intent);


            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_create_crv, menu);
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
}
