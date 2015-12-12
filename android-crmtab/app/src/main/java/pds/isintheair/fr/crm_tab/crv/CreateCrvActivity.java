package pds.isintheair.fr.crm_tab.crv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import pds.isintheair.fr.crm_tab.R;

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
        if(id == R.id.action_save){
            //call service

            HttpRequestTask request = new HttpRequestTask();
            request.setActivity(this);
            request.execute();
            Toast.makeText(this, "calling httprequest", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

