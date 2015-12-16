package badge.system.pds.crmbadge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckActivity extends Activity {
    String tagId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        tagId = getIntent().getStringExtra("tagID");
        // makeRequest("http://192.168.1.53:8080/api/doBadge",tagId);
        get(tagId);
//        Log.e("recieved tag: ", tagId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check, menu);
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

    public void get(String id){

        // TODO Auto-generated method stub

        URL url;
        Log.d("rest_service", "entered");
        try {
            url = new URL("http://192.168.1.53:8080/api/doBadge/"+ id);


            HttpURLConnection urlConnection;

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            String result = input.readLine();

            Toast toast = Toast.makeText(this, result, Toast.LENGTH_LONG);
            toast.show();

            Log.d("RESULT",result);



        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }





    }

}
