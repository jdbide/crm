package pds.isintheair.fr.crm_tab.crv;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Muthu on 12/12/2015.
 */
//class for http request
class HttpRequestTask extends AsyncTask<Activity, Void, Void> {
private Context activity;

    public void setActivity(Activity activity){
        this.activity = activity;
    }
    @Override
    protected Void doInBackground(Activity... params) {


        try {
            JSONObject crvObject = new JSONObject();
            crvObject.put("id",1);
            crvObject.put("commercial",1);
            crvObject.put("date",System.currentTimeMillis());
            crvObject.put("satisfaction","oui");
            crvObject.put("comment","client très satisfait");
            crvObject.put("contact",1);
            crvObject.put("client",1);
            crvObject.put("visit",1);

            JSONArray productArray = new JSONArray();
            JSONObject product = new JSONObject();
            product.put("id",1);
            productArray.put(product);

            crvObject.put("product", productArray);

            JSONObject mainObject = new JSONObject();
            mainObject.put("report", crvObject);


            final String url = "http://192.168.1.53:8080/api/crv/addCrv";



            makeRequest(url, mainObject.toString());
        } catch (Exception e) {
            Log.e("HttpRequestTask", e.getMessage(), e);
        }

        return null;
    }

    public  String makeRequest(String uri, String json) {
        HttpURLConnection urlConnection;
        String url;
        String data = json;
        String result = null;
        try {
            //Connect
            urlConnection = (HttpURLConnection) ((new URL(uri).openConnection()));
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            //Write
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(data);
            writer.close();
            outputStream.close();

            //Read
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            bufferedReader.close();
            result = sb.toString();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}