package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import pds.isintheair.fr.crmtab.R;

public class AbstractActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract);
        ButterKnife.bind(this);
    }
}
