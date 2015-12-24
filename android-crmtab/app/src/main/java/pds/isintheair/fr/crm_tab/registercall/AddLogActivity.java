package pds.isintheair.fr.crm_tab.registercall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import pds.isintheair.fr.crm_tab.R;

public class AddLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_log_activity);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


}
