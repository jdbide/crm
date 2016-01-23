package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;


/**
 * Created by jbide on 22/01/2016.
 */

public class LoginActivity extends Activity {
    @Bind(R.id.loginemail)
    EditText          mail;
    @Bind(R.id.loginpassword)
    EditText          pass;
    @Bind(R.id.btnSuivant)
    Button            con;
    @Bind(R.id.loadingPanel)
    RelativeLayout    loading;
    @Bind(R.id.LogincoordinatorLayout)
    CoordinatorLayout coordlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        SharedPreferences        prefs  = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        //Test if user is already logged(data id is in sharedpreferences)
        if (prefs.getString("id", "") != "")
            startActivity(new Intent(this, MainActivity.class));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ButterKnife.bind(this);
        loading.setVisibility(View.GONE);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                User user = new User();
                String credentials = mail.getText().toString()+":" + pass.getText().toString();
                byte[] data = credentials.getBytes(StandardCharsets.UTF_8);
                final String basic =
                        Base64.encodeToString(data, Base64.NO_WRAP);
                user.setPassword(basic);
                login(user, getApplicationContext(), loading, coordlayout);
                loading.setVisibility(View.VISIBLE);
                */

                //TODO Remove it and uncomment
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

    }
}