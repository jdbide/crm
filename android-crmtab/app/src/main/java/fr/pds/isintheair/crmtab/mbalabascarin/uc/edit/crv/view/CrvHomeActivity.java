package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import fr.pds.isintheair.crmtab.R;


public class CrvHomeActivity extends AppCompatActivity {

    Fragment clientListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crv_home);
    }
    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_crv, menu);
        return true;
    }

}
