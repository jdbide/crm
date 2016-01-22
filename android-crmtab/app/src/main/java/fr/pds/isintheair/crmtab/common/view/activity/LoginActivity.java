package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import fr.pds.isintheair.crmtab.common.model.User;

import static fr.pds.isintheair.crmtab.common.controller.LoginService.login;

/**
 * Created by jbide on 22/01/2016.
 */

public class LoginActivity extends Activity {
    @Bind(R.id.loginemail)
    EditText mail;
    @Bind(R.id.loginpassword)
    EditText pass;
    @Bind(R.id.btnSuivant)
    Button con;
    @Bind(R.id.loadingPanel)
    RelativeLayout loading;
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
        loading.setVisibility(View.GONE);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(pass.getText().toString());
                user.setPassword(pass.getText().toString());
                login(user,getApplicationContext(),loading,coordlayout);
                loading.setVisibility(View.VISIBLE);
            }
        });

    }
}