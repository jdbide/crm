package pds.isintheair.fr.crm_tab.registercall;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import pds.isintheair.fr.crm_tab.R;

public class StartCallRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_call_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    public void go(View v){
        LogDialogBoxFragment fragment1 = new LogDialogBoxFragment();
        // fragment1.mListener = MainActivity.this;
        //fragment1.text = mTextView.getText().toString();
        fragment1.show(getFragmentManager(), "klhkjm");


    }

}
