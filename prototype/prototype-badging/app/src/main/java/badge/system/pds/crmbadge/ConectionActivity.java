package badge.system.pds.crmbadge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConectionActivity extends Activity {

    TextView statusConnection;
    ImageView imgStatus;
    String status="";
    Intent service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conection);
        service = new Intent(this, RESTclient.class);
        status = getIntent().getStringExtra("status");
        statusConnection = (TextView)findViewById(R.id.status);
        imgStatus = (ImageView)findViewById(R.id.imgStatus);

        if(status.equals("true")){
            imgStatus.setImageResource(R.drawable.sucess);
            statusConnection.setText("Badgé le " + printStandardDate(new Date()));
           // MyClientTask myClientTask = new MyClientTask(Config.SOCKET_SERVER_IP,8082);
           // myClientTask.execute();

        }else{
            imgStatus.setImageResource(R.drawable.wrong);
            statusConnection.setText("Echec de badge!");
        }

    }





    private String printStandardDate(Date date) {
        return new SimpleDateFormat("dd/MM/yy HH:mm").format(date);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_conection, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(status.equals("true")){
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Quitter")
                    .setMessage("Êtes-vous sûr de quitter?")
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }).setNegativeButton("Non", null).show();
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }



}
