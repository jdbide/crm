package badge.system.pds.crmbadge;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RESTclient extends IntentService {

    private ImageView imgStatus;
    String tagId="";
    public RESTclient() {
        super("RESTclient");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        tagId = intent.getStringExtra("tagID");


        get(tagId);
        Log.e("recieved tag: ", tagId);
}

    public void get(String id){

        // TODO Auto-generated method stub

        URL url;
        Log.d("rest_service", "entered");
        try {
            url = new URL(Config.API_URL + id);


            HttpURLConnection urlConnection;

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            String result = input.readLine();
            if(result.equals("true")){
                setStatus();


                Intent intent = new Intent(this, ConectionActivity.class);
                //intent.setAction("test");
                intent.putExtra("status", result);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            /*LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.activity_main, null);
            ImageView imgStatus = (ImageView) layout.findViewById(R.id.imgStatus);
            //if(result.equals("conected")){
                imgStatus.setImageResource(R.drawable.sucess);
            //}*/

           // intent.putExtra("status", result);
            //sendBroadcast(intent);
            //startActivity(intent);


            //Log.d("RESULT",result);



            } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void setStatus(){

        // TODO Auto-generated method stub

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();

        URL url;
        Log.d("rest_service", "entered");
        try {
            url = new URL(Config.API_URL_CHANGE_STATUS + deviceId);


            HttpURLConnection urlConnection;

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            String result = input.readLine();



            /*Intent intent = new Intent(this, ConectionActivity.class);
            //intent.setAction("test");
            intent.putExtra("status",result);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            /*LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.activity_main, null);
            ImageView imgStatus = (ImageView) layout.findViewById(R.id.imgStatus);
            //if(result.equals("conected")){
                imgStatus.setImageResource(R.drawable.sucess);
            //}*/

            // intent.putExtra("status", result);
            //sendBroadcast(intent);
            //startActivity(intent);


            //Log.d("RESULT",result);



        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }




}
