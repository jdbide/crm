package fr.pds.isintheair.crmtab.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.gson.Gson;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.helper.FbDBHelper;
import fr.pds.isintheair.crmtab.model.entity.FbEventsPojo.Data;

public class FbEventActivity extends Activity {
    Data data;
    FbDBHelper mydb;
    String description, hdebut, hfin, lieu, title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_event);
        data = (Data) getIntent().getSerializableExtra("data");

        mydb = new FbDBHelper(this);
        final Gson gson = new Gson();

        try {
            if(data.getName() != null){
                title = data.getName();
            }else{
                title = " - ";
            }

            if(data.getDescription() != null){
                description = data.getDescription();
            }else{
                description = " - ";
            }

            if(data.getStart_time() != null){
                hdebut = data.getStart_time();
            }else{
                hdebut = " - ";
            }

            if(data.getEnd_time() != null){
                hfin = data.getEnd_time();
            }else{
                hfin = " - ";
            }

            if(data.getPlace() != null){
                lieu = data.getPlace().getName();
            }else{
                lieu = " - ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Voulez vous ajouter l'évenement " + data.getName() +" dans votre agenda?");
        // set dialog message
        alertDialogBuilder
                .setMessage("Description: " + description+"\n"
                        + "Heure début: " + hdebut + "\n"
                        + "Heure fin: " + hfin + "\n"
                        + "Lieu: " + lieu)

                .setCancelable(false)
                .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        mydb.insertEvent(gson.toJson(data,Data.class));

                        FbEventActivity.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        mydb.insertPendingEvent(gson.toJson(data,Data.class));
                        FbEventActivity.this.finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }



}
