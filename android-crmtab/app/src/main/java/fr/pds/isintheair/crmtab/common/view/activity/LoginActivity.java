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
import fr.pds.isintheair.crmtab.common.model.database.entity.User;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.service.CalendarService;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.service.CallService;


/**
 * Created by jbide on 22/01/2016.
 */

/**
 * Fixed by jbide on 26/01/2016 : 17:00
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

        ButterKnife.bind(this);

        mail.setText("test@crm.fr");
        pass.setText("motdepasse");

        SharedPreferences        prefs  = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        //Test if user is already logged(data id is in sharedpreferences)
        if (!prefs.getString("id", "").equals(""))
            startActivity(new Intent(this, MainActivity.class));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ButterKnife.bind(this);
        loading.setVisibility(View.GONE);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail("");
                user.save();

                startService(new Intent(LoginActivity.this, CallService.class));
                startService(new Intent(LoginActivity.this, CalendarService.class));
                startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                /* TryLogin(mail.getText().toString(), pass.getText().toString(), getApplicationContext(), loading, coordlayout);
                loading.setVisibility(View.VISIBLE);*/
            }
        });

        con.callOnClick();
    }

}