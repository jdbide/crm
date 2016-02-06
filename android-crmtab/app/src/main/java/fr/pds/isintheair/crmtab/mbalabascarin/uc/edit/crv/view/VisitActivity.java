package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.adapter.VisitAdapter;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock.MockVisit;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Visit;

public class VisitActivity extends AppCompatActivity {

    List<Visit> visits;
    ListView visitList;
    VisitAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        visitList = (ListView) findViewById(R.id.listVisit);
        setSupportActionBar(toolbar);

        visits = new MockVisit().getMockVisit();

        // Define a new Adapter

        adapter = new VisitAdapter(this, visits);
        adapter.notifyDataSetChanged();

        // Assign adapter to ListView
        visitList.setAdapter(adapter);
        // ListView Item Click Listener
        visitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

            }

        });
    }
    
}
