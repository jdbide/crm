package pds.isintheair.fr.crmtab.crv.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pds.isintheair.fr.crmtab.R;

public class CrvHomeActivity extends AppCompatActivity {

    Fragment clientListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crv_home);
    }
}
