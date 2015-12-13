package pds.isintheair.fr.crm_tab.crv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pds.isintheair.fr.crm_tab.R;

public class CreateCrvActivity extends AppCompatActivity {

    TextView commercial, date, contact, tel, comment, client;
    CheckBox ch1, ch2, ch3, ch4;
    Button btnMessageList;
    ListView listView ;
    CardView card;
    List<String> messages = new ArrayList<String>();
    String userId, clientId, conatcId, visitId;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_crv);
        commercial = (TextView)findViewById(R.id.txtName);
        date = (TextView)findViewById(R.id.txtDate);
        contact = (TextView)findViewById(R.id.txtContact);
        tel = (TextView)findViewById(R.id.txtTel);
        comment = (EditText) findViewById(R.id.txtComment);
        btnMessageList =(Button) findViewById(R.id.btnMessageList);
        card = (CardView) findViewById(R.id.card_view2);
        client = (TextView) findViewById(R.id.lblInfoClient);
    radioGroup = (RadioGroup) findViewById(R.id.grpSatisfaction);

        ch1 = (CheckBox) findViewById(R.id.chk1);
        ch2 = (CheckBox) findViewById(R.id.chk2);
        ch3 = (CheckBox) findViewById(R.id.chk3);
        ch4 = (CheckBox) findViewById(R.id.chk4);

        //mock preformated messages
        messages.add("Client très satisfait");
        messages.add("Client pas satisfait");
        messages.add("Important");
        messages.add("A voir plus tard");


        Intent intent = getIntent();
        String mockValue = intent.getStringExtra("mock");
        ch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()) {
                    //checked
                    card.setVisibility(View.VISIBLE);
                } else {
                    //not checked
                    card.setVisibility(View.INVISIBLE);
                }

            }
        });

        try {
            JSONObject json = new JSONObject(mockValue);
            JSONObject mockObject = new JSONObject(json.getJSONObject("mock").toString());
            commercial.setText(mockObject.get("user").toString());
            contact.setText(mockObject.get("contact").toString());
            tel.setText(mockObject.get("tel").toString());
            //ch1.setChecked(true);
            client.setText(mockObject.get("client").toString()+" - "+mockObject.get("address").toString());
            clientId = mockObject.get("clientId").toString();
            userId = mockObject.get("userId").toString();
            conatcId = mockObject.get("contactId").toString();
            visitId = mockObject.get("visitId").toString();
           int rand = RandomInformation.randInt(1,4);
            if(rand == 1){
                ch1.setChecked(true);


            }
            else if(rand == 2){
                ch2.setChecked(true);

            }
            else if(rand == 3){
                ch3.setChecked(true);

            }
            else if(rand == 4){
                ch4.setChecked(true);
                card.setVisibility(View.VISIBLE);

            }






        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    public void launchInputDialog(View v){
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(CreateCrvActivity.this);
        View promptView = layoutInflater.inflate(R.layout.msg_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateCrvActivity.this);
        alertDialogBuilder.setView(promptView);

        // Get ListView object from xml
        listView = (ListView) promptView.findViewById(R.id.lstMessages);

        // Define a new Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, messages);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                comment.append(" " + itemValue);
                // Show Alert
                /*Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();*/


            }

        });

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)

                .setNegativeButton("Fermer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    public String createJson(){
        JSONObject data = new JSONObject();
        JSONObject mock = new JSONObject();

        int selectedID = radioGroup.getCheckedRadioButtonId();

        try {
            data.put("id", userId);
            data.put("commercial", userId);
            data.put("date", System.currentTimeMillis());

            if(selectedID == 1){
                data.put("satisfaction", "oui");
            }else if(selectedID == 2){
                data.put("satisfaction", "non");
            }else if(selectedID == 3){
                data.put("satisfaction", "moyen");
            }
            data.put("comment", comment.getText().toString());
            data.put("contact", conatcId);
            data.put("client", clientId);
            data.put("visit", visitId);

            mock.put("mock", data);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mock.toString();
    }

    public void editInfo(View view){
       // commercial.setEnabled(true);
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
            request.setActivity("create");
            request.setRequestJson(createJson());
            request.execute();
            Toast.makeText(this, "calling httprequest", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

