package pds.isintheair.fr.crm_tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class CreateCrvActivity extends AppCompatActivity {

    TextView commercial, date, contact, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_crv);
        commercial = (TextView)findViewById(R.id.txtName);
        date = (TextView)findViewById(R.id.txtDate);
        contact = (TextView)findViewById(R.id.txtContact);
        tel = (TextView)findViewById(R.id.txtTel);


    }

    public void editInfo(View view){
        commercial.setEnabled(true);
        date.setEnabled(true);
        contact.setEnabled(true);
        tel.setEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_crv, menu);
        return true;
    }
}
