package fr.pds.isintheair.crmtab.common.view.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.User;

import static fr.pds.isintheair.crmtab.common.controller.LoginService.login;

/**
 * Created by jbide on 22/01/2016.
 */

public class LoginActivity extends Activity {
    @Bind(R.id.email)
    EditText mail;
    @Bind(R.id.password)
    EditText pass;
    @Bind(R.id.btnSuivant)
    EditText con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(pass.getText().toString());
                user.setPassword(pass.getText().toString());
                login(user);
            }
        });

    }
}