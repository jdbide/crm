package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.pds.isintheair.crmtab.R;


public class CrvHomeActivity extends AppCompatActivity {

    Fragment clientListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crv_home);
    }
}
