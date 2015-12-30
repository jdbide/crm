package pds.isintheair.fr.crm_tab.crv.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.crv.cache.CacheDao;
import pds.isintheair.fr.crm_tab.crv.mock.RandomInformation;
import pds.isintheair.fr.crm_tab.crv.model.Client;
import pds.isintheair.fr.crm_tab.crv.model.Report;
import pds.isintheair.fr.crm_tab.crv.model.Reporting;
import pds.isintheair.fr.crm_tab.crv.retrofit.Service;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CreateCrvActivity extends AppCompatActivity {

    //Init all var
    TextView commercial, date, contact, tel, comment, client;
    CheckBox ch1, ch2, ch3, ch4;
    Button btnMessageList, btnList;
    ListView listView , lstProducts;
    CardView card;
    List<String> messages = new ArrayList<String>();
    String userId, clientId, conatcId, visitId;
    RadioGroup radioGroup;
    List<String> presentedProducts = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    CacheDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_crv);


        //get all preformatted messages from cache
        dao = new CacheDao(this);
        messages = dao.getAllMessages();


        //get all views
        commercial = (TextView)findViewById(R.id.txtName);
        date = (TextView)findViewById(R.id.txtDate);
        contact = (TextView)findViewById(R.id.txtContact);
        tel = (TextView)findViewById(R.id.txtTel);
        comment = (EditText) findViewById(R.id.txtComment);
        btnMessageList =(Button) findViewById(R.id.btnMessageList);
        card = (CardView) findViewById(R.id.card_view2);
        client = (TextView) findViewById(R.id.lblInfoClient);
        radioGroup = (RadioGroup) findViewById(R.id.grpSatisfaction);
        lstProducts = (ListView) findViewById(R.id.lstProducts);
        btnList = (Button) findViewById(R.id.btnList);

        ch1 = (CheckBox) findViewById(R.id.chk1);
        ch2 = (CheckBox) findViewById(R.id.chk2);
        ch3 = (CheckBox) findViewById(R.id.chk3);
        ch4 = (CheckBox) findViewById(R.id.chk4);


        //get mocked client object
        Intent intent = getIntent();

        Client cl = (Client)intent.getSerializableExtra("ClientObject");


        //if "presentation prosuit" selected, enable product list button to show mocked product list
        ch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()) {
                    //checked
                    btnList.setEnabled(true);
                } else {
                    //not checked
                    btnList.setEnabled(false);
                    presentedProducts.clear();
                    adapter.notifyDataSetChanged();
                }

            }
        });



        try {

            //Mock pre formated information
            JSONObject json = new JSONObject(new RandomInformation().getRandomInfo());
            JSONObject mockObject = new JSONObject(json.getJSONObject("mock").toString());
            commercial.setText(mockObject.get("user").toString());
            contact.setText(mockObject.get("contact").toString());
            tel.setText(mockObject.get("tel").toString());



            //Mock a visit
            int rand = RandomInformation.randInt(1,4);

            client.setText(cl.getClientSurname() +" "+cl.getClientName()+" -- "+cl.getClientAddress());
            clientId =  Integer.toString(cl.getClientId());
            userId = "1";
            conatcId = Integer.toString(rand);
            visitId = Integer.toString(rand);


            //Select a random visit report subject
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
              //  lstProducts.setVisibility(View.VISIBLE);
                btnList.setEnabled(true);

            }



            //Init presented Product list
            // Define a new Adapter
             adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, presentedProducts);


            // Assign adapter to ListView
            lstProducts.setAdapter(adapter);


            // ListView Item Click Listener
            lstProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) listView.getItemAtPosition(position);

                    //launch a new dialog box to delete if a product is selected
                    showDialogBox(position);

                }

            });


        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    //Dialog box that enables the option to delete a product in the list
    public void showDialogBox(final int position){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateCrvActivity.this);

        // set title
        alertDialogBuilder.setTitle(":)");

        // set dialog message
        alertDialogBuilder
                .setMessage("Êtes-vous sûr de supprimer ce produit?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        presentedProducts.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


//Init mocked product lsit
    public void launchInputDialogProduct(View v){

        List<String> products = new ArrayList<String>();
        for(int i=0; i<20 ; i++){
            products.add("Product "+i);
        }
        LayoutInflater layoutInflater = LayoutInflater.from(CreateCrvActivity.this);
        View promptView = layoutInflater.inflate(R.layout.msg_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateCrvActivity.this);
        alertDialogBuilder.setView(promptView);

        // Get ListView object from xml
        listView = (ListView) promptView.findViewById(R.id.lstMessages);

        // Define a new Adapter
         ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, products);


        // Assign adapter to ListView
        listView.setAdapter(adapter1);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                //comment.append(" " + itemValue);
                // Show Alert
                /*Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();*/
                if (!presentedProducts.contains(itemValue)) {
                    presentedProducts.add(itemValue);
                }

                adapter.notifyDataSetChanged();
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

    // launch dialog box with pre formated message list
    public void launchInputDialog(View v){
        messages = dao.getAllMessages();
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

        adapter.notifyDataSetChanged();

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

    public void createReport(){
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.53:8080/api/crv/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Service iService = retrofit.create(Service.class);


        //create reporting object

        int selectedID = radioGroup.getCheckedRadioButtonId();

        RadioButton satisfaction = (RadioButton)findViewById(selectedID);


        Reporting reporting = new Reporting();
        Report report = new Report();
        report.setId("1");
        report.setCommercial(userId);
        report.setDate(Long.toString(System.currentTimeMillis()));
        report.setSatisfaction(satisfaction.getText().toString());
        report.setComment(comment.getText().toString());
        report.setClient(clientId);
        report.setContact(conatcId);
        report.setVisit(visitId);

        reporting.setReport(report);

        Call<Boolean> call = iService.createReport(reporting);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Response<Boolean> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Log.i("rest response", "Report created");
                    Toast.makeText(CreateCrvActivity.this, "Compte rendu de visite a bien été crée :)", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreateCrvActivity.this, "Response: "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                    Log.e("rest response", t.toString());
            }
        });
    }

    public String createJson(){
        JSONObject data = new JSONObject();
        JSONObject mock = new JSONObject();

        int selectedID = radioGroup.getCheckedRadioButtonId();

        RadioButton satisfaction = (RadioButton)findViewById(selectedID);

        try {
            data.put("id", userId);
            data.put("commercial", userId);
            data.put("date", System.currentTimeMillis());

            data.put("satisfaction", satisfaction.getText());
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


    //this method enables first block in UI to edit fields
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

            //call HttpRequestTask to send json to REST server
           // HttpRequestTask request = new HttpRequestTask();

            //tell that it comes from create report activity to send
            //json to correct url end point

           // request.setActivity("create");
            //request.setRequestJson(createJson());
            //request.execute();
            createReport();
            Toast.makeText(this, "calling httprequest", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Launch launchPreformattedMessageActivity
    public void launchPreformattedMessageActivity(View view){
        Intent intent = new Intent(this, CrvPreformatedMessage.class);
        startActivity(intent);
    }

}

